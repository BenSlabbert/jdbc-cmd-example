package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String text = null;
        Scanner input = new Scanner(System.in);

        String name;
        int age;
        String type;

        while (!"2".equals(text)) {
            System.out.println("Please choose an option: \n\n1 - add a pet\n2 - exit");
            text = input.nextLine();

            switch (text) {
                case "1":
                    System.out.println("What's your pet's name?");
                    name = input.nextLine();
                    System.out.println("How old is your pet?");
                    String petAge = input.nextLine();

                    try {
                        age = Integer.parseInt(petAge);
                    } catch (Exception e) {
                        throw new Exception("Invalid input. Age must be a name");
                    }

                    System.out.println("What type of animal is it?");
                    type = input.nextLine();
                    System.out.println(name + " " + "is" + " " + age + " " + "years old" + " " + "and he/she is a" + " " + type);
                    System.out.println();
                    addToDB(name, age, type);
                    break;
                case "2":
                    System.out.println("Exiting ...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not recognized");
            }
        }

    }

    private static void addToDB(String name, int age, String type) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pets", "root", "root")) {
            String sql = "INSERT INTO pet (name, age, type) values (?, ?, ?)";
            executePreparedStatement(conn, sql, name, age, type);
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getCause());
            e.printStackTrace();
        }
    }

    private static void executePreparedStatement(Connection conn, String sql, String name, int age, String type) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, type);

            int numberOfUpdates = stmt.executeUpdate();
            System.out.println("This must be 1 : " + numberOfUpdates);
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getCause());
            e.printStackTrace();
        }
    }
}
