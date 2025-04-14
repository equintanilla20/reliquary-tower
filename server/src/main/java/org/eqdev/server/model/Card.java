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
     * Card(card_name,card_rarity,card_type,cmc,card_colors,card_color_identity,card_set,card_set_name,card_text,card_artist,card_image_uri_small,card_image_uri_normal,card_image_uri_large,card_image_uri_png,card_image_uri_art_crop,card_image_uri_border_crop)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_rarity")
    private String cardRarity;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "cmc")
    private Double cmc;

    @Column(name = "card_colors")
    private String cardColors;

    @Column(name = "card_color_identity")
    private String cardColorIdentity;

    @Column(name = "card_set")
    private String cardSet;

    @Column(name = "card_set_name")
    private String cardSetName;

    @Column(name = "card_text", length = 1024)
    private String cardText;

    @Column(name = "card_artist")
    private String cardArtist;

    @Column(name = "card_image_uri_small")
    private String cardImageUrlSmall;

    @Column(name = "card_image_uri_normal")
    private String cardImageUrlNormal;

    @Column(name = "card_image_uri_large")
    private String cardImageUrlLarge;

    @Column(name = "card_image_uri_png")
    private String cardImageUrlPng;

    @Column(name = "card_image_uri_art_crop")
    private String cardImageUrlArtCrop;

    @Column(name = "card_image_uri_border_crop")
    private String cardImageUrlBorderCrop;

    // CONSTRUCTORS
    public Card() {}
    public Card(Long id) { this.id = id; }
    public Card(
        String cardName,
        String cardRarity,
        String cardType,
        Double cmc,
        String cardColors,
        String cardColorIdentity,
        String cardSet,
        String cardSetName,
        String cardText,
        String cardArtist,
        String cardImageUrlSmall,
        String cardImageUrlNormal,
        String cardImageUrlLarge,
        String cardImageUrlPng,
        String cardImageUrlArtCrop,
        String cardImageUrlBorderCrop
    ) {
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.cardType = cardType;
        this.cmc = cmc;
        this.cardColors = cardColors;
        this.cardColorIdentity = cardColorIdentity;
        this.cardSet = cardSet;
        this.cardSetName = cardSetName;
        this.cardText = cardText;
        this.cardArtist = cardArtist;
        this.cardImageUrlSmall = cardImageUrlSmall;
        this.cardImageUrlNormal = cardImageUrlNormal;
        this.cardImageUrlLarge = cardImageUrlLarge;
        this.cardImageUrlPng = cardImageUrlPng;
        this.cardImageUrlArtCrop = cardImageUrlArtCrop;
        this.cardImageUrlBorderCrop = cardImageUrlBorderCrop;
    }

    // GETTERS
    public Long getId() { return id; }
    public String getCardName() { return cardName; }
    public String getCardRarity() { return cardRarity; }
    public String getCardType() { return cardType; }
    public Double getCmc() { return cmc; }
    public String getCardColors() { return cardColors; }
    public String getCardColorIdentity() { return cardColorIdentity; }
    public String getCardSet() { return cardSet; }
    public String getCardSetName() { return cardSetName; }
    public String getCardText() { return cardText; }
    public String getCardArtist() { return cardArtist; }
    public String getCardImageUrlSmall() { return cardImageUrlSmall; }
    public String getCardImageUrlNormal() { return cardImageUrlNormal; }
    public String getCardImageUrlLarge() { return cardImageUrlLarge; }
    public String getCardImageUrlPng() { return cardImageUrlPng; }
    public String getCardImageUrlArtCrop() { return cardImageUrlArtCrop; }
    public String getCardImageUrlBorderCrop() { return cardImageUrlBorderCrop; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardRarity(String cardRarity) { this.cardRarity = cardRarity; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setCmc(Double cmc) { this.cmc = cmc; }
    public void setCardColors(String cardColors) { this.cardColors = cardColors; }
    public void setCardColorIdentity(String cardColorIdentity) { this.cardColorIdentity = cardColorIdentity; }
    public void setCardSet(String cardSet) { this.cardSet = cardSet; }
    public void setCardSetName(String cardSetName) { this.cardSetName = cardSetName; }
    public void setCardText(String cardText) { this.cardText = cardText; }
    public void setCardArtist(String cardArtist) { this.cardArtist = cardArtist; }
    public void setCardImageUrlSmall(String cardImageUrlSmall) { this.cardImageUrlSmall = cardImageUrlSmall; }
    public void setCardImageUrlNormal(String cardImageUrlNormal) { this.cardImageUrlNormal = cardImageUrlNormal; }
    public void setCardImageUrlLarge(String cardImageUrlLarge) { this.cardImageUrlLarge = cardImageUrlLarge; }
    public void setCardImageUrlPng(String cardImageUrlPng) { this.cardImageUrlPng = cardImageUrlPng; }
    public void setCardImageUrlArtCrop(String cardImageUrlArtCrop) { this.cardImageUrlArtCrop = cardImageUrlArtCrop; }
    public void setCardImageUrlBorderCrop(String cardImageUrlBorderCrop) { this.cardImageUrlBorderCrop = cardImageUrlBorderCrop; }

    // Override
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardName='" + cardName + '\'' +
                ", cardRarity='" + cardRarity + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cmc=" + cmc +
                ", cardColors='" + cardColors + '\'' +
                ", cardColorIdentity='" + cardColorIdentity + '\'' +
                ", cardSet='" + cardSet + '\'' +
                ", cardSetName='" + cardSetName + '\'' +
                ", cardText='" + cardText + '\'' +
                ", cardArtist='" + cardArtist + '\'' +
                ", cardImageUrlSmall='" + cardImageUrlSmall + '\'' +
                ", cardImageUrlNormal='" + cardImageUrlNormal + '\'' +
                ", cardImageUrlLarge='" + cardImageUrlLarge + '\'' +
                ", cardImageUrlPng='" + cardImageUrlPng + '\'' +
                ", cardImageUrlArtCrop='" + cardImageUrlArtCrop + '\'' +
                ", cardImageUrlBorderCrop='" + cardImageUrlBorderCrop + '\'' +
                '}';
    }
}