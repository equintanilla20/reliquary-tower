package org.eqdev.server.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "deck")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deck_name", unique = true)
    private String deckName;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    // Relationships
    @ElementCollection
    @CollectionTable(name = "deck_cards", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "card_id")
    private List<Long> cardIds;
    
    @ManyToOne
    @JoinColumn(name = "user_id") // Specifies the foreign key column
    private User user;

    // CONSTRUCTORS
    public Deck() {}
    public Deck(Long id) { this.id = id; }
    public Deck(Long id) { this.id = id; }
    public Deck(String deckName, List<Long> cardIds) {
        this.deckName = deckName;
        this.cardIds = cardIds;
    }
    
    // GETTERS
    public Long getId() { return id; }
    public String getDeckName() { return deckName; }
    public List<Long> getCardIds() { return cardIds; }
    public Long getId() { return id; }
    public String getDeckName() { return deckName; }
    public List<Long> getCardIds() { return cardIds; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setDeckName(String deckName) { this.deckName = deckName; }
    public void setCardIds(List<Long> cardIds) { this.cardIds = cardIds; }
    public void addCardId(Long cardId) { this.cardIds.add(cardId); }
    public void removeCardId(Long cardId) { this.cardIds.remove(cardId); }
    public void setId(Long id) { this.id = id; }
    public void setDeckName(String deckName) { this.deckName = deckName; }
    public void setCardIds(List<Long> cardIds) { this.cardIds = cardIds; }
    public void setUserId(Long userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", deckName='" + deckName + '\'' +
                ", cardIds=" + cardIds +
                '}';
    }
}