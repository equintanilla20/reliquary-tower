package org.eqdev.server.controller;

import org.eqdev.server.model.Deck;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

import org.eqdev.server.service.DeckService;


@Controller
@Slf4j
public class DeckGraphQLController {
    private final DeckService deckService;

    public DeckGraphQLController(DeckService deckService) {
        this.deckService = deckService;
    }

    @QueryMapping
    public Deck deckById(@Argument Long deckId) {
        log.info("GraphQL Query received: deckById for deck ID: {}", deckId);
        return deckService.getDeckById(deckId);
    }

    @QueryMapping
    public java.util.List<Deck> allDecksForUser(@Argument String username) {
        log.info("GraphQL Query received: allDecksForUser for username: {}", username);
        return deckService.allDecksForUser(username);
    }

    @MutationMapping
    public Deck createDeck(@Argument String deckName, @Argument String username) {
        log.info("GraphQL Mutation received: createDeck for deck name: {}", deckName);
        return deckService.createDeck(deckName, username);
    }

    @MutationMapping
    public Deck updateDeck(@Argument Long deckId, @Argument String deckName, @Argument String username) {
        log.info("GraphQL Mutation received: updateDeck for deck ID: {}, new name: {}", deckId, deckName);
        return deckService.updateDeck(deckId, deckName, username);
    }

    @MutationMapping
    public boolean deleteDeck(@Argument Long deckId, @Argument String username) {
        log.info("GraphQL Mutation received: deleteDeck for deck ID: {}", deckId);
        return deckService.deleteDeck(deckId, username);
    }

    @MutationMapping
    public Deck addCardToDeck(@Argument Long deckId, @Argument Long cardId, @Argument int quantity, @Argument String username) {
        log.info("GraphQL Mutation received: addCardToDeck for deck ID: {}, card ID: {}, quantity: {}", deckId, cardId, quantity);
        return deckService.addCardToDeck(deckId, cardId, quantity, username);
    }

    @MutationMapping
    public Deck removeCardFromDeck(@Argument Long deckId, @Argument Long cardId, @Argument int quantity, @Argument String username) {
        log.info("GraphQL Mutation received: removeCardFromDeck for deck ID: {}, card ID: {}, quantity: {}", deckId, cardId, quantity);
        return deckService.removeCardFromDeck(deckId, cardId, quantity, username);
    }
}
