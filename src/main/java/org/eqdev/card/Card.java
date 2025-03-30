package org.eqdev.card;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {
    // Extract the fields from the table Card
    // Table: Card(card_id, card_name, card_rarity, card_set, card_text, card_image_url)
    @Id
    @GeneratedValue()
    private Integer cardId;

    private String cardName;
    private String cardRarity;
    private String cardSet;
    
    @Column(name = "card_text", columnDefinition = "TEXT")
    private String cardText;

    private String cardImageUrl;

    // Constructors, getters, and setters
    public Card() { }

    public Card(String cardName, String cardRarity, String cardSet, String cardText, String cardImageUrl) {
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.cardSet = cardSet;
        this.cardText = cardText;
        this.cardImageUrl = cardImageUrl;
    }

    // GETTERS
    public static Card getByCardId(Integer cardId) {
        // This method should be implemented to retrieve a Card by its ID from the database
        return null; // Placeholder return statement
    }
    public Integer geCardId() { return cardId; }
    public String  getCardName() { return cardName; }
    public String  getCardRarity() { return cardRarity; }
    public String  getCardSet() { return cardSet; }
    public String  getCardText() { return cardText; }
    public String  getCardImageUrl() { return cardImageUrl; }

    // SETTERS
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardRarity(String cardRarity) { this.cardRarity = cardRarity; }
    public void setCardSet(String cardSet) { this.cardSet = cardSet; }
    public void setCardText(String cardText) {this.cardText = cardText; }
    public void setCardImage(String cardImage) { this.cardImageUrl = cardImage; }
}
