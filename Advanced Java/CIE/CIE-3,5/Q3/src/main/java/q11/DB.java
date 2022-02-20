package q11;
import java.sql.*;


public class DB {
	public static Connection initialize() throws Exception {
		Connection con = null;
		String url ="jdbc:mysql://localhost:3306/";
		String db = "cie1";
		String user = "root";
		String password = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			
			Class.forName(driver).getDeclaredConstructor().newInstance();
			con = DriverManager.getConnection(url+db, user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
