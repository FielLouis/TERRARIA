package com.mygdx.game.Utilities;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DATABASE_NAME = "dbCITerraria";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void createDatabase() {
        Connection c = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            statement = c.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;

            statement.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound Exception");
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTableUser() {
        try (Connection c = getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS tblusers" +
                             "( id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                             "uname VARCHAR(50) NOT NULL," +
                             "upassword VARCHAR(50) NOT NULL, " +
                             "cutsceneDone BOOLEAN NOT NULL DEFAULT FALSE," +
                             "bossDone BOOLEAN NOT NULL DEFAULT FALSE" +
                             ")"
             )) {
            c.setAutoCommit(false);
            statement.execute();
            c.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
