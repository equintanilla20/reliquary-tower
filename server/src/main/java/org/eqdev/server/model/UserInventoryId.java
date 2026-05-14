package org.eqdev.server.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserInventoryId implements Serializable {

    private Long userId;
    private Long cardId;

    public UserInventoryId() {}

    public UserInventoryId(Long userId, Long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }

    // Getters
    public Long getUserId() { return userId; }
    public Long getCardId() { return cardId; }

    // Setters
    public void setUserId(Long userId) { this.userId = userId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInventoryId that = (UserInventoryId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(cardId, that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cardId);
    }
}