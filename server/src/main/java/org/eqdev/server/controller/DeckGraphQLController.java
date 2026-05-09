package org.eqdev.server.controller;

import org.eqdev.server.model.Deck;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.eqdev.server.service.DeckService;


@Controller
public class DeckGraphQLController {
    private final DeckService deckService;

    public DeckGraphQLController(DeckService deckService) {
        this.deckService = deckService;
    }

    @QueryMapping
    public Deck deckById(@Argument Long deckId) {
        return deckService.getDeckById(deckId);
    }

    @QueryMapping
    public java.util.List<Deck> allDecksForUser(@Argument String username) {
        return deckService.allDecksForUser(username);
    }

    @MutationMapping
    public Deck createDeck(@Argument String deckName, @Argument String username) {
        return deckService.createDeck(deckName, username);
    }

    @MutationMapping
    public Deck updateDeck(@Argument Long deckId, @Argument String deckName, @Argument String username) {
        return deckService.updateDeck(deckId, deckName, username);
    }

    @MutationMapping
    public boolean deleteDeck(@Argument Long deckId, @Argument String username) {
        return deckService.deleteDeck(deckId, username);
    }

    @MutationMapping
    public Deck addCardToDeck(@Argument Long deckId, @Argument Long cardId, @Argument int quantity, @Argument String username) {
        return deckService.addCardToDeck(deckId, cardId, quantity, username);
    }

    @MutationMapping
    public Deck removeCardFromDeck(@Argument Long deckId, @Argument Long cardId, @Argument int quantity, @Argument String username) {
        return deckService.removeCardFromDeck(deckId, cardId, quantity, username);
    }
}
