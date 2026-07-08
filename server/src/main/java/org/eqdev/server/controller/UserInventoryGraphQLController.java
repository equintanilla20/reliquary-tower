package org.eqdev.server.controller;

import org.eqdev.server.service.UserInventoryService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.data.method.annotation.Argument;

@Controller
@Slf4j
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
        log.info("GraphQL Mutation received: addCardToInventory for user ID: {}, card ID: {}, quantity: {}", userId, cardId, quantity);
        userInventoryService.addCardToInventory(userId, cardId, quantity);
    }
}
