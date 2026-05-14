package org.eqdev.server.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "user_inventory")
@EntityListeners(AuditingEntityListener.class)
public class UserInventory {

    @EmbeddedId
    private UserInventoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cardId")
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(name = "is_foil")
    private boolean isFoil = false;

    public UserInventory() {}

    public UserInventory(AppUser user, Card card, Integer quantity) {
        this.id = new UserInventoryId(user.getId(), card.getCardId());
        this.user = user;
        this.card = card;
        this.quantity = quantity;
    }
    
    // Getters
    public UserInventoryId getId() { return id; }
    public AppUser getUser() { return user; }
    public Card getCard() { return card; }
    public Integer getQuantity() { return quantity; }
    public boolean isFoil() { return isFoil; }

    // Setters
    public void setId(UserInventoryId id) { this.id = id; }
    public void setUser(AppUser user) { this.user = user; }
    public void setCard(Card card) { this.card = card; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setFoil(boolean isFoil) { this.isFoil = isFoil; }
}
