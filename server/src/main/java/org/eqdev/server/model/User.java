package org.eqdev.server.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    /*
     * This class represents a user stored in a PostgreSQL database.
     * Users maintain a collection of cards and decks.
     * User(user_name, user_email, user_password_hash, user_password_salt)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password_hash")
    private String userPasswordHash;

    @Column(name = "user_password_salt")
    private String userPasswordSalt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Deck> decks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_cards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> library = new HashSet<>();

    // CONSTRUCTORS
    public User() {}
    public User(Long id) { this.id = id; }
    public User(String userName, String userEmail, String userPasswordHash, String userPasswordSalt) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPasswordHash = userPasswordHash;
        this.userPasswordSalt = userPasswordSalt;
    }

    // GETTERS
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public String getUserPasswordHash() { return userPasswordHash; }
    public String getUserPasswordSalt() { return userPasswordSalt; }
    public Set<Deck> getDecks() { return decks; }
    public Set<Card> getLibrary() { return library; }
    public void addDeck(Deck deck) {
        decks.add(deck);
        deck.setUserId(this.getId());
    }
    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserPasswordHash(String userPasswordHash) {
        this.userPasswordHash = userPasswordHash;
    }
    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }
    public void setDecks(Set<Deck> decks) {
        this.decks = decks;
    }
    public void setLibrary(Set<Card> library) {
        this.library = library;
    }
    
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", decks=" + decks +
            ", library=" + library +
            '}';
    }
}
