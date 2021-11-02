package com.codegym.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 11/2/2021
 * Time: 3:09 PM
 */
public class DBConnection {
    public static Connection conn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                System.out.println("Connecting...");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/pet_db", "anhnbt", "KhoaiTay@2019");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
