package org.eqdev.server.controller;

import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.repository.AppUserRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserGraphQLController {
    
    private final AppUserRepository appUserRepository;

    public UserGraphQLController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @QueryMapping
    public Iterable<AppUser> users() {
        return appUserRepository.findAll();
    }

    @QueryMapping
    public AppUser userByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    @QueryMapping
    public AppUser userByEmail(String email) {
        AppUser user = appUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }
}
