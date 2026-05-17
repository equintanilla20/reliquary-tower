package org.eqdev.server.controller;

import org.eqdev.server.service.UserInventoryService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

@Controller
public class UserInventoryGraphQLController {
    public final UserInventoryService userInventoryService;
    
    public UserInventoryGraphQLController(UserInventoryService userInventoryService) {
        this.userInventoryService = userInventoryService;
    }

    @MutationMapping
    public void addCardToInventory(
        @Argument Long userId,
        @Argument Long cardId,
        @Argument Integer quantity
    ) {
        userInventoryService.addCardToInventory(userId, cardId, quantity);
    }
}
