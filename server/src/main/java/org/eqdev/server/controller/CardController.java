package org.eqdev.server.controller;

import org.eqdev.server.model.Card;
import org.eqdev.server.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CardController {

    private static final Logger log = LoggerFactory.getLogger(CardController.class);
    
    private final CardRepository cardRepository;
    
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /* 
     * QUERYS
     */
    @QueryMapping
    public List<Card> getAllCards() {
        log.info("Fetching all cards from the database.");
        return cardRepository.findAll();
    }

    @QueryMapping
    public Card getCardById(@Argument Long id) {
        log.info("Fetching card with ID: {}", id);
        if (id == null) {
            log.warn("Card ID is null.");
            return null;
        }
        return cardRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Card> getCardsBySet(@Argument String cardSet) {
        log.info("Fetching cards from set: {}", cardSet);
        if (cardSet == null || cardSet.isEmpty()) {
            log.warn("Card set is null or empty.");
            return List.of();
        }
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
        log.info("Adding new card: {} (Rarity: {}, Set: {})", cardName, cardRarity, cardSet);
        Card card = new Card();
        card.setCardName(cardName);
        card.setCardRarity(cardRarity);
        card.setCardSet(cardSet);
        card.setCardText(cardText);
        card.setCardImageUrl(cardImageUrl);
        return cardRepository.save(card);
    }
}