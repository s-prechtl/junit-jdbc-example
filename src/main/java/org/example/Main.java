package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       Connection connection = DbInstance.getInstance();
       PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            System.out.println(result.getString("first_name"));
        }
    }
}
