package org.eqdev.server.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deck_card") // Snake_case is standard for Postgres
public class DeckCard {

    @EmbeddedId
    private DeckCardId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("deckId")
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cardId")
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public DeckCard() {}

    public DeckCard(Deck deck, Card card, Integer quantity) {
        this.deck = deck;
        this.card = card;
        this.quantity = quantity;
        this.id = new DeckCardId(deck.getDeckId(), card.getCardId());
    }

    public DeckCardId getId() { return id; }
    public Deck getDeck() { return deck; }
    public Card getCard() { return card; }
    public Integer getQuantity() { return quantity; }

    public void setId(DeckCardId id) { this.id = id; }
    public void setDeck(Deck deck) { this.deck = deck; }
    public void setCard(Card card) { this.card = card; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckCard deckCard = (DeckCard) o;
        return Objects.equals(id, deckCard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}