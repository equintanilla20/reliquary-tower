package org.eqdev.server.controller;

import java.util.List;
import java.util.Optional;

import org.eqdev.server.dto.CardFilter;
import org.eqdev.server.model.Card;
import org.eqdev.server.service.CardService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CardGraphQLController {
    private final CardService cardService;

    public CardGraphQLController(CardService cardService) {
        this.cardService = cardService;
    }

    @QueryMapping
    public List<Card> allCards(@Argument int page, @Argument int size) {
        log.info("GraphQL Query received: allCards with page: {} and size: {}", page, size);
        return cardService.allCards(page, size);
    }

    @QueryMapping
    public List<Card> searchCards(@Argument CardFilter filter, @Argument int page, @Argument int size) {
        log.info("GraphQL Query received: searchCards with filter: {}, page: {}, size: {}", filter, page, size);
        return cardService.searchCardsAdvanced(filter, page, size);
    }

    @QueryMapping
    public Optional<Card> cardByCardId(@Argument Long cardId) {
        log.info("GraphQL Query received: cardByCardId for card ID: {}", cardId);
        return cardService.getCardById(cardId);
    }

    @QueryMapping
    public List<Card> cardsByRarity(@Argument String rarity) {
        log.info("GraphQL Query received: cardsByRarity for rarity: {}", rarity);
        return cardService.searchCardsByRarity(rarity);
    }

    @QueryMapping
    public List<Card> cardsByType(@Argument String type) {
        log.info("GraphQL Query received: cardsByType for type: {}", type);
        return cardService.searchCardsByType(type);
    }

    @QueryMapping
    public List<Card> cardsByColor(@Argument String color) {
        log.info("GraphQL Query received: cardsByColor for color: {}", color);
        return cardService.searchCardsByColor(color);
    }

    @QueryMapping
    public List<Card> cardsByColorIdentity(@Argument String colorIdentity) {
        log.info("GraphQL Query received: cardsByColorIdentity for color identity: {}", colorIdentity);
        return cardService.searchCardsByColorIdentity(colorIdentity);
    }

    @QueryMapping
    public List<Card> cardsBySet(@Argument String set) {
        log.info("GraphQL Query received: cardsBySet for set: {}", set);
        return cardService.searchCardsBySet(set);
    }

    @QueryMapping
    public List<Card> cardsBySetName(@Argument String setName) {
        log.info("GraphQL Query received: cardsBySetName for set name: {}", setName);
        return cardService.searchCardsBySetName(setName);
    }

}
