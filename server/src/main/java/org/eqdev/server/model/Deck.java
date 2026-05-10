package org.eqdev.server.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.repository.AppUserRepository;

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

    AppUserRepository appUserRepository;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @Column(name = "deck_name", nullable = false)
    private String deckName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeckCard> cards = new ArrayList<>();

    private String notes;

    public Deck() {}

    // Getters
    public Long getDeckId() { return deckId; }
    public String getDeckName() { return deckName; }
    public AppUser getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getNotes() { return notes; }
    public List<DeckCard> getDeckCards() { return cards; }


    // Setters
    public void setDeckId(Long deckId) { this.deckId = deckId; }
    public void setDeckName(String deckName) { this.deckName = deckName; }
    public void setUser(String username) { 
        AppUser newUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        this.user = newUser; 
    }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setDeckCards(List<DeckCard> cards) { this.cards = cards; }
}