package TodoDAO;
import java.io.*;
import java.sql.*;

public class Database {

    public static String URL = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";

    private Connection conn;
    
    public Database() {
	try {
	    Class.forName("org.sqlite.JDBC");
	    this.conn = DriverManager.getConnection(URL);
	} catch (ClassNotFoundException e) {
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
    
    public ResultSet getTodos(int userId) throws SQLException {
	String sql1 = "SELECT TodoId,TodoText FROM todo WHERE UserId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, userId);
	return stmt1.executeQuery();
    }

    public ResultSet getTodo1(int userId, int todoId) throws SQLException {
	String sql1 = "SELECT TodoText FROM todo WHERE UserId = ? AND TodoId = ?;";
	PreparedStatement stmt1 = conn.prepareStatement(sql1);
	stmt1.setInt(1, userId);
	stmt1.setInt(2, todoId);
	return stmt1.executeQuery();
    }
}
