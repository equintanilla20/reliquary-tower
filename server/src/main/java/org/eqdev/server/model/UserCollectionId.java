package org.eqdev.server.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserCollectionId implements Serializable{

    private Long userId;
    private Long cardId;

    public UserCollectionId() {}

    public UserCollectionId(Long userId, Long cardId) {
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

        UserCollectionId that = (UserCollectionId) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return cardId != null ? !cardId.equals(that.cardId) : that.cardId == null;
    }

}
