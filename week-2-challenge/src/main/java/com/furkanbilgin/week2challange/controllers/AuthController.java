package com.furkanbilgin.week2challange.controllers;

import com.furkanbilgin.week2challange.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;

public enum AuthController {
    INSTANCE;
    private static final String PASSWORD_SALT = "Ajpo6EP+QgXlBpU";

    public String hashPassword(String password) {
        try {
            // Use SHA1 + salt to hash the password
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
            byte[] salt = PASSWORD_SALT.getBytes(); // Replace with your actual salt
            md.update(salt);
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public boolean authUser(HttpServletRequest request, String username, String password) {
        // Check if the user exists in the database
        var user = DatabaseController.getCrud().getUser(username, hashPassword(password));
        if (user == null) {
            return false;
        }
        request.getSession().setAttribute("user", user);
        return true;
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
