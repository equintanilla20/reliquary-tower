package org.eqdev.server.service;

import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.repository.AppUserRepository;
import org.springframework.stereotype.Service;


@Service
public class AppUserService {
    private final AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }
        AppUser newUser = new AppUser(username, email, password, "ROLE_USER");
        return userRepository.save(newUser);
    }

    public AppUser getByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User" + username + " not found"));
    }
}
