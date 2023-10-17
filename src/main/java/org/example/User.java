package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 */
@Getter
@Setter
public class User {
    private int userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(ResultSet result) throws SQLException {
        setUserId(result.getInt("user_id"));
        setEmail(result.getString("email"));
        setPassword(result.getString("password"));
        setFirstName(result.getString("first_name"));
        setLastName(result.getString("last_name"));
    }

    public static User getUserById(int userId) throws SQLException {
        User user = null;
        Connection connection = DbInstance.getInstance();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = " + userId);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            user = new User(result);
        }


        return user;
    }

    public List<Todo> getTodos() throws SQLException {
	    return this.getTodos(null);
    }

    /**
     * Loads all todos by given status
     * @param done status of todos; If null status will be ignored.
     * @return
     * @throws SQLException
     */
    public List<Todo> getTodos(boolean done) throws SQLException {
        List<Todo> todos = new ArrayList<Todo>();
        Connection connection = DbInstance.getInstance();
	String statement = "SELECT * FROM todo WHERE user_id = " + userId;
	if (done != null) {
		statement += " AND done = " + done;	
	}
        PreparedStatement statement = connection.prepareStatement(statement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            todos.add(new Todo(result));
        }
        return todos;
    }
}
