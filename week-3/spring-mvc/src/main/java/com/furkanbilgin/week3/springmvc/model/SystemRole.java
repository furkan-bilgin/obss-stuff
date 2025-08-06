package com.furkanbilgin.week3.springmvc.model;

public enum SystemRole {
    GUEST,
    USER,
    ADMIN;

    public static SystemRole fromString(String role) {
        try {
            return SystemRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
