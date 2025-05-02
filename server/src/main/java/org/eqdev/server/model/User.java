package org.eqdev.server.model;

public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private String role; // "USER" or "ADMIN"
    
    // CONSTRUCTORS
    public User() {}
    
    public User(Long id) {
        this.id = id;
    }
    
    public User(String username, String passwordHash, String email, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }
    
    // GETTERS
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
