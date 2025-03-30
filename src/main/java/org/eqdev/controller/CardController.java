package org.eqdev.controller;

import org.eqdev.card.Card;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

public class CardController {
    @QueryMapping
    public Card cardById(String id) {
        return Card.getByCardId(id);
    }
    @QueryMapping
    public String cardByName(String name) {
        return "Card with Name: " + name;
    }
    @QueryMapping
    public String cardByType(String type) {
        return "Card with Type: " + type;
    }
    @QueryMapping
    public String cardByRarity(String rarity) {
        return "Card with Rarity: " + rarity;
    }
    @QueryMapping
    public String cardBySet(String set) {
        return "Card with Set: " + set;
    }

    @SchemaMapping
    public String cardDetails(String card) {
        return "Details of Card: " + card;
    }
}
