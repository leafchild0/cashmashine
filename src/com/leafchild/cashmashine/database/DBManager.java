package com.leafchild.cashmashine.database;

import java.sql.*;

/**
 * Created by: leaf
 * Project: cashmashinedemo
 * Date: 7/7/15
 * Time: 11:25 PM
 */
public class DBManager {

        private static Connection conn;

        private static void initializeConnection() {

                try {
                        Class.forName("org.h2.Driver");
                        conn = DriverManager.getConnection("jdbc:h2:file:/home/leaf/dev/java/db/h2/cashmachine", "h2", "cash");
                } catch (ClassNotFoundException e) {
                        System.out.println("Exception: " + e);
                } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                }

        }

        private static void getTables() {
                ResultSet rs;
                try {
                        Statement st = conn.createStatement();
                        rs = st.executeQuery("SELECT * FROM CUSTOMER_CARDS");
                        //rs = st.executeQuery("SELECT * FROM CUSTOMER_CARDS");
                        while(rs != null) {
                                System.out.println(rs.getString("CARD_ID"));
                        }
                } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                }
        }

        public static void main(String[] args) {
                initializeConnection();
                getTables();
                closeConnection();
        }

        private static void closeConnection() {
                try {
                        conn.close();
                } catch (SQLException e) {
                        System.out.println("Exception: " + e);
                }
        }

}
