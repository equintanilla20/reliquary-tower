package org.eqdev.server.controller;

import org.eqdev.server.service.UserInventoryService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserInventoryGraphQLController {
    public final UserInventoryService inventoryService;
    
    public UserInventoryGraphQLController(UserInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @MutationMapping
    public void addCardToInventory(String username, Long cardId, Integer quantity) {
        inventoryService.addCardToInventory(username, cardId, quantity);
    }
}
