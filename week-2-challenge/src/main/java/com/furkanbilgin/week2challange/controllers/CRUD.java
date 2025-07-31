package com.furkanbilgin.week2challange.controllers;

import com.furkanbilgin.week2challange.models.Contact;
import com.furkanbilgin.week2challange.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private final Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }

    public User getUser(String username, String password) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND passwordHash = ?");
            statement.setString(1, username);
            statement.setString(2, AuthController.INSTANCE.hashPassword(password));
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("passwordHash")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking user existence", e);
        }
        return null;
    }

    public List<Contact> getContacts(int userId) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM contacts WHERE userId = ?");
            statement.setInt(1, userId);
            var resultSet = statement.executeQuery();
            List<Contact> contacts = new ArrayList<>();
            while (resultSet.next()) {
                contacts.add(getContactFromResultSet(resultSet));
            }
            return contacts;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching contacts", e);
        }
    }

    public List<Contact> searchContacts(int userId, String searchTerm) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM contacts WHERE userId = ? AND (contactName LIKE ? OR contactNumber LIKE ?)");
            statement.setInt(1, userId);
            statement.setString(2, "%" + searchTerm + "%");
            statement.setString(3, "%" + searchTerm + "%");
            var resultSet = statement.executeQuery();
            List<Contact> contacts = new ArrayList<>();
            while (resultSet.next()) {
                contacts.add(getContactFromResultSet(resultSet));
            }
            return contacts;
        } catch (Exception e) {
            throw new RuntimeException("Error searching contacts", e);
        }
    }

    private static Contact getContactFromResultSet(ResultSet resultSet) throws SQLException {
        return new Contact(
                resultSet.getInt("id"),
                resultSet.getInt("userId"),
                resultSet.getString("contactName"),
                resultSet.getString("contactNumber")
        );
    }

    public void deleteContact(int userId, int contactId) {
        try {
            var statement = connection.prepareStatement("DELETE FROM contacts WHERE userId = ? AND id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, contactId);
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting contact", e);
        }
    }

    public void createContact(String contactName, String contactNumber, int userId) {
        try {
            var statement = connection.prepareStatement("INSERT INTO contacts (contactName, contactNumber, userId) VALUES (?, ?, ?)");
            statement.setString(1, contactName);
            statement.setString(2, contactNumber);
            statement.setInt(3, userId);
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error creating contact", e);
        }
    }

    public Contact getContactById(int userId, int contactId) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM contacts WHERE userId = ? AND id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, contactId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getContactFromResultSet(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching contact by ID", e);
        }
        return null;
    }

    public void updateContact(int contactId, String contactName, String contactNumber, int userId) {
        try {
            var statement = connection.prepareStatement("UPDATE contacts SET contactName = ?, contactNumber = ? WHERE id = ? AND userId = ?");
            statement.setString(1, contactName);
            statement.setString(2, contactNumber);
            statement.setInt(3, contactId);
            statement.setInt(4, userId);
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error updating contact", e);
        }
    }
}
