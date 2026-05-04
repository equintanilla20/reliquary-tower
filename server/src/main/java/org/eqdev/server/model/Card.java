package org.eqdev.server.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "card_name", nullable = false)
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

    @Column(name = "card_keywords")
    private String cardKeywords;

    @Column(name = "card_text")
    private String text;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> legalities = new HashMap<>();

    @Column(name = "card_artist")
    private String artist;

    @Column(name = "card_image_uri_small")
    private String imageUriSmall;

    @Column(name = "card_image_uri_normal")
    private String imageUriNormal;

    @Column(name = "card_image_uri_large")
    private String imageUriLarge;

    @Column(name = "card_image_uri_png")
    private String imageUriPng;

    @Column(name = "card_image_uri_art_crop")
    private String artCropUri;

    @Column(name = "card_image_uri_border_crop")
    private String borderCropUri;

    public Card() {}

    public Card(String cardName, 
                String cardRarity, 
                String cardType, 
                Double cmc, 
                String cardColors, 
                String cardColorIdentity,
                String cardSet, 
                String cardSetName, 
                String cardKeywords,
                String text, 
                String artist, 
                String imageUriSmall, 
                String imageUriNormal,
                String imageUriLarge, 
                String imageUriPng, 
                String artCropUri, 
                String borderCropUri) {
        this.cardName = cardName;
        this.cardRarity = cardRarity;
        this.cardType = cardType;
        this.cmc = cmc;
        this.cardColors = cardColors;
        this.cardColorIdentity = cardColorIdentity;
        this.cardSet = cardSet;
        this.cardSetName = cardSetName;
        this.cardKeywords = cardKeywords;
        this.text = text;
        this.artist = artist;
        this.imageUriSmall = imageUriSmall;
        this.imageUriNormal = imageUriNormal;
        this.imageUriLarge = imageUriLarge;
        this.imageUriPng = imageUriPng;
        this.artCropUri = artCropUri;
        this.borderCropUri = borderCropUri;
    }

    public record LegalityEntry(String format, String status) {}

    // Getters
    public Long getCardId() { return cardId; }
    public String getCardName() { return cardName; }
    public String getCardRarity() { return cardRarity; }
    public String getCardType() { return cardType; }
    public Double getCmc() { return cmc; }
    public String getCardColors() { return cardColors; }
    public String getCardColorIdentity() { return cardColorIdentity; }
    public String getCardSet() { return cardSet; }
    public String getCardSetName() { return cardSetName; }
    public String getText() { return text; }
    public String getCardKeywords() { return cardKeywords; }
    public String getArtist() { return artist; }
    public String getImageUriSmall() { return imageUriSmall; }
    public String getImageUriNormal() { return imageUriNormal; }
    public String getImageUriLarge() { return imageUriLarge; }
    public String getImageUriPng() { return imageUriPng; }
    public String getArtCropUri() { return artCropUri; }
    public String getBorderCropUri() { return borderCropUri; }
    public List<LegalityEntry> getLegalities() {
        if (this.legalities == null) return Collections.emptyList();
        return this.legalities.entrySet().stream()
                .map(entry -> new LegalityEntry(entry.getKey(), entry.getValue()))
                .toList();
    }

    // Setters
    public void setCardId(Long cardId) { this.cardId = cardId; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    public void setCardRarity(String cardRarity) { this.cardRarity = cardRarity; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setCmc(Double cmc) { this.cmc = cmc; }
    public void setCardColors(String cardColors) { this.cardColors = cardColors; }
    public void setCardColorIdentity(String cardColorIdentity) { this.cardColorIdentity = cardColorIdentity; }
    public void setCardSet(String cardSet) { this.cardSet = cardSet; }
    public void setCardSetName(String cardSetName) { this.cardSetName = cardSetName; }
    public void setCardKeywords(String cardKeywords) { this.cardKeywords = cardKeywords; }
    public void setText(String text) { this.text = text; }
    public void setLegalities(Map<String, String> legalities) { this.legalities = legalities; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setImageUriSmall(String imageUriSmall) { this.imageUriSmall = imageUriSmall; }
    public void setImageUriNormal(String imageUriNormal) { this.imageUriNormal = imageUriNormal; }
    public void setImageUriLarge(String imageUriLarge) { this.imageUriLarge = imageUriLarge; }
    public void setImageUriPng(String imageUriPng) { this.imageUriPng = imageUriPng; }
    public void setArtCropUri(String artCropUri) { this.artCropUri = artCropUri; }
    public void setBorderCropUri(String borderCropUri) { this.borderCropUri = borderCropUri; }
}
