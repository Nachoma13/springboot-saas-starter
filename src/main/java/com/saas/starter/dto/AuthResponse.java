package com.saas.starter.dto;

public record AuthResponse(
    String token,
    String tokenType,
    long expiresIn,
    UserInfo user
) {
    public record UserInfo(Long id, String email, String firstName, String lastName, String role) {}
}
