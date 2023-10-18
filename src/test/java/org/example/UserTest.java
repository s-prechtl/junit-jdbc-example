package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;

/**
 * UserTest
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
    private Connection connection;

    @BeforeAll
    public void setUp() throws SQLException {
        connection = DbInstance.getInstance(true);
        purgeAllData();
        Statement statement = connection.createStatement();
        statement.execute(
                "INSERT INTO user (user_id, email, password, first_name, last_name) VALUES (1, 'sprechtl@htl-steyr.ac.at', 'beidl', 'Stefan', 'Prechtl')");
        statement.execute(
                "INSERT INTO user (user_id, email, password, first_name, last_name) VALUES (2, 'jweissen@htl-steyr.ac.at', 'broadabeidl', 'Jonas', 'Weissensteiner')");
        statement.execute(
                "INSERT INTO user (user_id, email, password, first_name, last_name) VALUES (3, 'dhain@htl-steyr.ac.at', 'blindschleich', 'David', 'Hain')");
        statement.execute(
                "INSERT INTO user (user_id, email, password, first_name, last_name) VALUES (4, 'jhuber@htl-steyr.ac.at', 'anaconda', 'Julian', 'Huber')");

        statement.execute("INSERT INTO todo (todo_id, user_id, description, done) VALUES (1, 1, 'Things', 1)");
        statement.execute("INSERT INTO todo (todo_id, user_id, description, done) VALUES (2, 1, 'undone Things', 0)");
        statement.execute("INSERT INTO todo (todo_id, user_id, description, done) VALUES (3, 2, 'other Stuff', 1)");
        statement.execute(
                "INSERT INTO todo (todo_id, user_id, description, done) VALUES (4, 2, 'undone other Stuff', 1)");
        statement.execute("INSERT INTO todo (todo_id, user_id, description, done) VALUES (5, 3, 'Stuff', 1)");
        statement.execute("INSERT INTO todo (todo_id, user_id, description, done) VALUES (6, 3, 'undone Stuff', 0)");
        statement.execute(
                "INSERT INTO todo (todo_id, user_id, description, done) VALUES (7, 4, 'League of Legends', 1)");
        statement.execute(
                "INSERT INTO todo (todo_id, user_id, description, done) VALUES (8, 4, 'Inting stefans games', 0)");
    }

    @AfterAll
    public void tearDown() throws SQLException {
        purgeAllData();
    }

    private void purgeAllData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM todo");
        statement.execute("DELETE FROM user");
    }

    @Test
    public void getById() throws SQLException {
        User user = User.getUserById(1);
        assertEquals("sprechtl@htl-steyr.ac.at", user.getEmail());
    }

    @Test
    public void getAllUsers() throws SQLException {
        List<User> users = User.getAllUsers();
        assertEquals(4, users.size());
    }

    @Test
    public void getTodos() throws SQLException {
        User user = User.getUserById(1);
        List<Todo> todos = user.getTodos();
        assertEquals(2, todos.size());
    }

    @Test
    public void getTodosByDoneState() throws SQLException {
        User user = User.getUserById(1);
        List<Todo> todos = user.getTodos(true);
        assertEquals(1, todos.size());

        todos = user.getTodos(false);
        assertEquals(1, todos.size());
    }
}
