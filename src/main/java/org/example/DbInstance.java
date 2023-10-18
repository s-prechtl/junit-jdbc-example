package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DbInstance
 */
public class DbInstance {
    private static Connection instance = null;

    private DbInstance(boolean testInstance) {
        Properties props = new Properties();
        try {
            String configPath = "config.properties";
            if (testInstance) {
                configPath = "config.test.properties";
            }
            props.load(DbInstance.class.getResource(configPath).openStream());

            instance = DriverManager.getConnection(props.getProperty("connection_url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        return getInstance(false);
    }


    public static Connection getInstance(boolean testInstance) {
        if (instance == null) {
            new DbInstance(testInstance);
        }
        return instance;
    }

}
