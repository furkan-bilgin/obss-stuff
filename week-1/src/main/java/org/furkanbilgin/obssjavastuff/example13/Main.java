package org.furkanbilgin.obssjavastuff.example13;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3307/db";
            String user = "user";
            String password = "pgmysql";
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url, user, password);
                    java.sql.Statement stmt = conn.createStatement();
                    java.sql.ResultSet rs = stmt.executeQuery("SELECT VERSION()")) {

                if (rs.next()) {
                    System.out.println("MySQL Version: " + rs.getString(1));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
