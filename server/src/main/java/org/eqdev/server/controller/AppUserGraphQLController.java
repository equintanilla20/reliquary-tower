package org.eqdev.server.controller;

import org.eqdev.server.dto.RegisterUser;
import org.eqdev.server.dto.UpdateUserProfile;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.service.AppUserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AppUserGraphQLController {
    
    private final AppUserService appUserService;

    public AppUserGraphQLController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @QueryMapping
    public Iterable<AppUser> allUsers() {
        return appUserService.getAllUsers();
    }

    @QueryMapping
    public AppUser userByUsername(@Argument String username) {
        return appUserService.getByUsername(username); 
    }

    @QueryMapping
    public AppUser userByEmail(@Argument String email) {
        return appUserService.getByEmail(email);
    }

    @MutationMapping
    public AppUser registerUser(@Argument RegisterUser input) {
        return appUserService.registerUser(input);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument Long id) {
        return appUserService.deleteUserAccount(id);
    }

    @MutationMapping
    public AppUser updateUserProfile(@Argument UpdateUserProfile input) {
        return appUserService.updateProfile(input);
    }
}