package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DbInstance
 */
public class DbInstance {
    private static Connection instance = null;

    private DbInstance() {
        Properties props = new Properties();
        try {
            props.load(DbInstance.class.getResource("config.properties").openStream());
            instance = DriverManager.getConnection(props.getProperty("connection_url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DbInstance();
        }
        return instance;
    }

}
