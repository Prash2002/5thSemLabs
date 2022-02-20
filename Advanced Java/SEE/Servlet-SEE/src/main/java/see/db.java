package see;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {
	public static Connection connection() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/see", "root", "");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
