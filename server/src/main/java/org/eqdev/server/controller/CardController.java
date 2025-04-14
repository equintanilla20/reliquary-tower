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
    public Card getCardById(@Argument Long id) {
        log.info("Fetching card with ID: {}", id);
        if (id == null) {
            log.warn("Card ID is null.");
            return null;
        }
        return cardRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Card> getCardByName(@Argument String cardName) {
        log.info("Fetching card by name: {}", cardName);
        if (cardName == null || cardName.isEmpty()) {
            log.warn("Card name is null or empty.");
            return null;
        }
        return cardRepository.findByCardName(cardName);
    }

    @QueryMapping
    public List<Card> getAllCards() {
        log.info("Fetching all cards from the database.");
        return cardRepository.findAll();
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

    @QueryMapping
    public List<Card> getCardsByRarity(@Argument String cardRarity) {
        log.info("Fetching cards by rarity: {}", cardRarity);
        if (cardRarity == null || cardRarity.isEmpty()) {
            log.warn("Card rarity is null or empty.");
            return List.of();
        }
        return cardRepository.findByCardRarity(cardRarity);
    }

    @QueryMapping
    public List<Card> getCardsByRarityAndSet(@Argument String cardRarity, @Argument String cardSet) {
        log.info("Fetching cards by rarity: {} and set: {}", cardRarity, cardSet);
        if (cardRarity == null || cardRarity.isEmpty() || cardSet == null || cardSet.isEmpty()) {
            log.warn("Card rarity or set is null or empty.");
            return List.of();
        }
        return cardRepository.findByCardRarityAndCardSet(cardRarity, cardSet);
    }

    /* 
     * MUTATIONS
     */
    @MutationMapping
    public Card addCard(
            @Argument String cardName,
            @Argument String cardRarity,
            @Argument String cardType,
            @Argument Double cmc,
            @Argument String cardColors,
            @Argument String cardColorIdentity,
            @Argument String cardSet,
            @Argument String cardSetName,
            @Argument String cardText,
            @Argument String cardArtist,
            @Argument String cardImageUrlSmall,
            @Argument String cardImageUrlNormal,
            @Argument String cardImageUrlLarge,
            @Argument String cardImageUrlPng,
            @Argument String cardImageUrlArtCrop,
            @Argument String cardImageUrlBorderCrop
    ) {
        log.info("Adding new card: {}", cardName);
        Card newCard = new Card(
                cardName,
                cardRarity,
                cardType,
                cmc,
                cardColors,
                cardColorIdentity,
                cardSet,
                cardSetName,
                cardText,
                cardArtist,
                cardImageUrlSmall,
                cardImageUrlNormal,
                cardImageUrlLarge,
                cardImageUrlPng,
                cardImageUrlArtCrop,
                cardImageUrlBorderCrop
        );
        return cardRepository.save(newCard);
    }
}