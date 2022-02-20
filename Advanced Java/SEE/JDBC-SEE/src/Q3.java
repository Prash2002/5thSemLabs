/*
 *  Write a Java-JDBC program to implement Banking Application using transaction management. 
 * Demonstrate Rollback and Savepoint concept. 
*/
import java.sql.*;

public class Q3 {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String dbUrl= "jdbc:mysql://localhost:3306/see";
		String username="root";
		String password ="";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			
			Statement st = con.createStatement();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
