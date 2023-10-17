package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

/**
 * Todo
 */
@Getter
@Setter
public class Todo {
   private int todoId;
   private User user;
   private String descirption;
   private boolean done;

   public Todo(ResultSet result) throws SQLException {
    setTodoId(result.getInt("todo_id"));
    setUser(User.getUserById(result.getInt("user_id")));
    setDescirption(result.getString("description"));
    setDone(result.getBoolean("done"));
   }
}
