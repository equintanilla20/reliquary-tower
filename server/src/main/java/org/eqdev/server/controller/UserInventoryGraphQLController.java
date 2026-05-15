package org.eqdev.server.controller;

import org.eqdev.server.model.AppUser;
import org.eqdev.server.service.UserInventoryService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.eqdev.server.repository.AppUserRepository;
import org.eqdev.server.exception.UserNotFoundException;

@Controller
public class UserInventoryGraphQLController {
    public final UserInventoryService inventoryService;
    public final AppUserRepository userRepository;
    
    public UserInventoryGraphQLController(
            UserInventoryService inventoryService, 
            AppUserRepository userRepository) {
        this.inventoryService = inventoryService;
        this.userRepository = userRepository;
    }

    @MutationMapping
    public void addCardToInventory(
        @Argument Long userId,
        @Argument Long cardId,
        @Argument Integer quantity
    ) {
        AppUser user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        inventoryService.addCardToInventory(user, cardId, quantity);
    }
}
