package TodoDAO;
import java.io.*;
import java.sql.*;
import java.util.*;

public interface Database {
    
    public void close() throws SQLException;
    
    public int login(String username) throws SQLException;
    
    public int signup(String username) throws SQLException;
    
    public int addTodo(int userId, String todoText) throws SQLException;

    public void changeTodo(int userId, int todoId, String todoText) throws SQLException;

    public void removeTodo(int userId, int todoId) throws SQLException;
    
    public ArrayList<TodoEntry> getTodos(int userId) throws SQLException;

    public TodoEntry getTodo1(int userId, int todoId) throws SQLException;
    
}
