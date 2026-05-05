package org.eqdev.server.controller;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.dto.CardFilter;
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
    public List<Card> allCards(@Argument int page, @Argument int size) {
        return cardService.allCards(page, size);
    }

    @QueryMapping
    public List<Card> searchCards(@Argument CardFilter filter, @Argument int page, @Argument int size) {
        return cardService.searchCardsAdvanced(filter, page, size);
    }

    @QueryMapping
    public Optional<Card> cardByCardId(@Argument Long cardId) {
        return cardService.getCardById(cardId);
    }

    @QueryMapping
    public List<Card> cardsByRarity(@Argument String rarity) {
        return cardService.searchCardsByRarity(rarity);
    }

    @QueryMapping
    public List<Card> cardsByType(@Argument String type) {
        return cardService.searchCardsByType(type);
    }

    @QueryMapping
    public List<Card> cardsByColor(@Argument String color) {
        return cardService.searchCardsByColor(color);
    }

    @QueryMapping
    public List<Card> cardsByColorIdentity(@Argument String colorIdentity) {
        return cardService.searchCardsByColorIdentity(colorIdentity);
    }

    @QueryMapping
    public List<Card> cardsBySet(@Argument String set) {
        return cardService.searchCardsBySet(set);
    }

    @QueryMapping
    public List<Card> cardsBySetName(@Argument String setName) {
        return cardService.searchCardsBySetName(setName);
    }

}
