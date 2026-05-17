package org.eqdev.server.dto;

public record RegisterUser(
    String username, 
    String email, 
    String password
) {}
