package org.eqdev.server.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")  // "USER" or "ADMIN"
    private String role;

    @Column(name = "decks")
    private List<Long> deckIds;
    
    // CONSTRUCTORS
    public User() {}
    public User(Long id) { this.id = id; }
    public User(String username, String passwordHash, String email, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }
    
    // GETTERS
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}
