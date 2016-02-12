package TodoDAO;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class MySQLDatabase implements Database {

    private Connection conn;
    
    public MySQLDatabase() {
	try {
	    InitialContext initCon = new InitialContext();
	    DataSource ds = (DataSource)initCon.lookup("java:comp/env/jdbc/todoApp");
	    this.conn = ds.getConnection();
	} catch (NamingException e) {
	    System.out.println(e);
	} catch (SQLException e) {
	    System.out.println(e);
	}
    }

    public void close() throws SQLException {
	this.conn.close();
	this.conn = null;
    }
    
    public int login(String username) throws SQLException {
	int userId = 0;
	String sql1 = "SELECT UserId FROM user WHERE username = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setString(1, username);
	ResultSet rs1 = stmt1.executeQuery();
	if (rs1.next()) {
	    userId = rs1.getInt(1);
	}
	return userId;
    }
    
    public int signup(String username) throws SQLException {
	int userId = 0;
	String sql1 = "SELECT max(UserId)+1 FROM user;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	ResultSet rs1 = stmt1.executeQuery();
	rs1.next();
	userId = rs1.getInt(1);
	String sql2 = "INSERT INTO user VALUES (?, ?)";
	PreparedStatement stmt2 = conn.prepareStatement(sql2);
	stmt2.setInt(1, userId);
	stmt2.setString(2, username);
	stmt2.execute();
	return userId;
    }
    
    public int addTodo(int userId, String todoText) throws SQLException {
	String sql1 = "SELECT max(TodoId)+1 FROM todo;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	ResultSet rs1 = stmt1.executeQuery();
	rs1.next();
	int todoId = rs1.getInt(1);
	String sql2 = "INSERT INTO todo VALUES (?, ?, ?);";
	PreparedStatement stmt2 = conn.prepareStatement(sql2);
	stmt2.setInt(1, todoId);
	stmt2.setInt(2, userId);
	stmt2.setString(3, todoText);
	stmt2.executeUpdate();
	return todoId;
    }

    public void changeTodo(int userId, int todoId, String todoText) throws SQLException {
	String sql1 = "UPDATE todo SET TodoText = ? WHERE TodoId = ? AND UserId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setString(1, todoText);
	stmt1.setInt(2, todoId);
	stmt1.setInt(3, userId);
	stmt1.executeUpdate();
    }

    public void removeTodo(int userId, int todoId) throws SQLException {
	String sql1 = "DELETE FROM todo WHERE TodoId = ? AND UserId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, todoId);
	stmt1.setInt(2, userId);
	stmt1.executeUpdate();
    }
    
    public ArrayList<TodoEntry> getTodos(int userId) throws SQLException {
	String sql1 = "SELECT TodoId,TodoText FROM todo WHERE UserId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, userId);
	ResultSet rs1 = stmt1.executeQuery();
	ArrayList<TodoEntry> entries = new ArrayList<TodoEntry>();
	while (rs1.next()) {
	    TodoEntry entry = new TodoEntry();
	    entry.setTodoId(rs1.getInt(1));
	    entry.setTodoText(rs1.getString(2));
	    entries.add(entry);
	}
	return entries;
    }

    public TodoEntry getTodo1(int userId, int todoId) throws SQLException {
	String sql1 = "SELECT TodoText FROM todo WHERE UserId = ? AND TodoId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, userId);
	stmt1.setInt(2, todoId);
	ResultSet rs1 = stmt1.executeQuery();
	TodoEntry entry = null;
	if (rs1.next()) {
	    entry = new TodoEntry();
	    entry.setTodoId(todoId);
	    entry.setTodoText(rs1.getString(1));
	}
	return entry;
    }
}
