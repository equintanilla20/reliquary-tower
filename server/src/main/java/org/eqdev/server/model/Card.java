package org.eqdev.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "card")
public class Card {
    /*
     * This class represents a Magic: The Gathering card stored in a PostgreSQL database.
     * It will be served using a GraphQL API.
     * Card(id, card_name, card_rarity, card_set, card_text, card_image_url)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_rarity")
    private String cardRarity;

    @Column(name = "card_set")
    private String cardSet;

    @Column(name = "card_text", length = 1024)
    private String cardText;

    @Column(name = "card_image_url")
    private String cardImageUrl;

    public Card() {}

    public Card(Long id) {
        this.id = id;
    }

    public Card(String cardName, String cardRarity, String cardSet, String cardText, String cardImageUrl) {
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.cardSet = cardSet;
        this.cardText = cardText;
        this.cardImageUrl = cardImageUrl;
    }

    public Long getCard() {
        return id;
    }

    public void setCard(Long cardId) {
        this.id = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    // Override toString for debugging
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardName='" + cardName + '\'' +
                ", cardRarity='" + cardRarity + '\'' +
                ", cardSet='" + cardSet + '\'' +
                ", cardText='" + cardText + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}';
    }
}