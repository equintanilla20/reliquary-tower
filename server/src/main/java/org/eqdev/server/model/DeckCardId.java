package org.eqdev.server.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DeckCardId implements Serializable {
    private Long deckId;
    private Long cardId;

    public DeckCardId() {}

    public DeckCardId(Long deckId, Long cardId) {
        this.deckId = deckId;
        this.cardId = cardId;
    }

    // Getters
    public Long getDeckId() { return deckId; }
    public Long getCardId() { return cardId; }

    // Setters
    public void setDeckId(Long deckId) { this.deckId = deckId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeckCardId that = (DeckCardId) o;

        if (deckId != null ? !deckId.equals(that.deckId) : that.deckId != null) return false;
        return cardId != null ? !cardId.equals(that.cardId) : that.cardId == null;
    }

    @Override
    public int hashCode() {
        int result = deckId != null ? deckId.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        return result;
    }
}
