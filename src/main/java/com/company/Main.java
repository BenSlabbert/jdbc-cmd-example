package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pets", "root", "root")) {
            String sql = "INSERT INTO pet (name, age, type) values (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "ben");
                stmt.setInt(2, 1);
                stmt.setString(3, "dog");

                int numberOfUpdates = stmt.executeUpdate();
                System.out.println("This must be 1 : " + numberOfUpdates);
            } catch (SQLException e) {
                System.err.println("SQL exception: " + e.getCause());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getCause());
            e.printStackTrace();
        }
    }
}
