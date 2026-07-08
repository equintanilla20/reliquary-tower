package org.eqdev.server.service;

import org.eqdev.server.dto.RegisterUser;
import org.eqdev.server.dto.UpdateUserProfile;
import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserService {
    private final AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser registerUser(RegisterUser input) {
        if (userRepository.existsByUsername(input.username())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(input.email())) {
            throw new RuntimeException("Email already in use");
        }
        AppUser newUser = new AppUser(input.username(), input.email(), input.password(), "ROLE_USER");
        return userRepository.save(newUser);
    }

    public boolean deleteUserAccount(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    public AppUser updateProfile(UpdateUserProfile input) {
        AppUser user = userRepository.findByUsername(input.username())
            .orElseThrow(() -> new UserNotFoundException("User with id " + input.username() + " not found"));
        
        if (input.username() != null && !input.username().equals(user.getUsername())) {
            if (userRepository.existsByUsername(input.username())) {
                throw new RuntimeException("Username already taken");
            }
            user.setUsername(input.username());
        }
        if (input.email() != null && !input.email().equals(user.getEmail())) {
            if (userRepository.existsByEmail(input.email())) {
                throw new RuntimeException("Email already in use");
            }
            user.setEmail(input.email());
        }
        
        if (input.password() != null) {
            user.setPassword(input.password());

        }
        return userRepository.save(user);
    }

    public AppUser getByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User" + username + " not found"));
    }

    public AppUser getByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }
}
