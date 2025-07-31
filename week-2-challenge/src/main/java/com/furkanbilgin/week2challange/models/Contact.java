package com.furkanbilgin.week2challange.models;

public class Contact {
    private final int id;
    private final int userId;
    private final String contactName;
    private final String contactNumber;

    public Contact(int id, int userId, String contactName, String contactNumber) {
        this.id = id;
        this.userId = userId;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
