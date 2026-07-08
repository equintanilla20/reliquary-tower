package org.eqdev.server.controller;

import org.eqdev.server.dto.RegisterUser;
import org.eqdev.server.dto.UpdateUserProfile;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.service.AppUserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AppUserGraphQLController {
    
    private final AppUserService appUserService;

    public AppUserGraphQLController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @QueryMapping
    public Iterable<AppUser> allUsers() {
        log.info("GraphQL Query received: allUsers");
        return appUserService.getAllUsers();
    }

    @QueryMapping
    public AppUser userByUsername(@Argument String username) {
        log.info("GraphQL Query received: userByUsername for username: {}", username);
        return appUserService.getByUsername(username); 
    }

    @QueryMapping
    public AppUser userByEmail(@Argument String email) {
        log.info("GraphQL Query received: userByEmail for email: {}", email);
        return appUserService.getByEmail(email);
    }

    @MutationMapping
    public AppUser registerUser(@Argument RegisterUser input) {
        log.info("GraphQL Mutation received: registerUser for username: {}", input.username());
        return appUserService.registerUser(input);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument Long id) {
        log.info("GraphQL Mutation received: deleteAccount for user ID: {}", id);
        return appUserService.deleteUserAccount(id);
    }

    @MutationMapping
    public AppUser updateUserProfile(@Argument UpdateUserProfile input) {
        log.info("GraphQL Mutation received: updateUserProfile for username: {}", input.username());
        return appUserService.updateProfile(input);
    }
}