package org.eqdev.server.controller;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.model.Card;
import org.eqdev.server.service.CardService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CardGraphQLController {
    private final CardService cardService;

    public CardGraphQLController(CardService cardService) {
        this.cardService = cardService;
    }

    @QueryMapping
    public List<Card> cards(@Argument int page, @Argument int size) {
        System.out.println("DEBUG ::: Fetching All Cards");
        return cardService.getAllCards(page, size);
    }

    @QueryMapping
    public List<Card> searchCards(@Argument String name, @Argument int page, @Argument int size) {
        return cardService.searchCardsByName(name, page, size);
    }

    @QueryMapping
    public Optional<Card> cardById(@Argument Long id) {
        return cardService.getCardById(id);
    }

}
