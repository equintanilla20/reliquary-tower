package org.eqdev.server.dto;

public record UpdateUserProfile(
    String username,
    String email,
    String password
) {}