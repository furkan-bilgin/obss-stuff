package com.furkanbilgin.week2challange.controllers;

import java.sql.Connection;
import java.sql.SQLException;

public enum DatabaseController {
    INSTANCE;

    private static final String DB_URL = "jdbc:mysql://localhost:3307/db";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "pgmysql";
    private CRUD crud;

    static {
        try {
            DatabaseController.INSTANCE.connect();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CRUD getCrud() {
        return INSTANCE.crud;
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        this.crud = new CRUD(connection);
    }
}
