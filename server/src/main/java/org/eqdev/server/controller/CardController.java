package org.eqdev.server.controller;

import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CardController {
    private final CardRepository cardRepository;
    
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /* 
     * QUERYS
     */
    @QueryMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @QueryMapping
    public Card getCardById(@Argument Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Card> getCardsBySet(@Argument String cardSet) {
        return cardRepository.findByCardSet(cardSet);
    }

    /* 
     * MUTATIONS
     */
    @MutationMapping
    public Card addCard(
            @Argument String cardName, 
            @Argument String cardRarity, 
            @Argument String cardSet, 
            @Argument String cardText, 
            @Argument String cardImageUrl) {
        Card card = new Card();
        card.setCardName(cardName);
        card.setCardRarity(cardRarity);
        card.setCardSet(cardSet);
        card.setCardText(cardText);
        card.setCardImageUrl(cardImageUrl);
        return cardRepository.save(card);
    }
}