package org.eqdev.server.controller;

import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.repository.AppUserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AppUserGraphQLController {
    
    private final AppUserRepository appUserRepository;

    public AppUserGraphQLController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @QueryMapping
    public Iterable<AppUser> users() {
        return appUserRepository.findAll();
    }

    @QueryMapping
    public AppUser userByUsername(@Argument String username) {
        AppUser user = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    @QueryMapping
    public AppUser userByEmail(@Argument String email) {
        AppUser user = appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    @MutationMapping
    public AppUser registerUser(@Argument String username, @Argument String email, @Argument String password) {
        AppUser newUser = new AppUser(username, email, password, "ROLE_USER");
        return appUserRepository.save(newUser);
    }
}
