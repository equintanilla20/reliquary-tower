package org.eqdev.server.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Deck")
public class Deck {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @Column(name = "deck_name", nullable = false)
    private String deckName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private AppUser user;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    public Deck() {}

    // Getters
    public Long getDeckId() { return deckId; }
    public String getDeckName() { return deckName; }
    public AppUser getUser() { return user; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    // Setters
    public void setDeckId(Long deckId) { this.deckId = deckId; }
    public void setDeckName(String deckName) { this.deckName = deckName; }
    public void setUser(AppUser user) { this.user = user; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}