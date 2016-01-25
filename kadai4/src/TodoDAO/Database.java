package TodoDAO;
import java.io.*;
import java.sql.*;

public class Database {

    public static String URL = "jdbc:sqlite:..\\webapps\\kadai4\\WEB-INF\\var\\todo.db";

    public static Connection getConnection() {
	Connection conn = null;
	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection(URL);
	} catch (ClassNotFoundException e) {
	} catch (SQLException e) {
	}
	return conn;
    }
    
}
