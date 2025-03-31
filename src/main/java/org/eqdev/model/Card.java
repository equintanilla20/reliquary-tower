package org.eqdev.model;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {
    // Extract the fields from the table Card
    // Table: Card(card_id, card_name, card_rarity, card_set, card_text, card_image_url)
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_generator")
    // @SequenceGenerator(name = "card_generator", sequenceName = "card_seq", allocationSize = 1)
    // private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "card_rarity", nullable = false)
    private String cardRarity;

    @Column(name = "card_set", nullable = false)
    private String cardSet;
    
    @Column(name = "card_text", columnDefinition = "TEXT")
    private String cardText;

    @Column(name = "card_image_url")
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
    public Long    getId() { return id; }
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

    // To String
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