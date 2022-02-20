import java.sql.*;

/* 
 Write a JAVA-JDBC program to create the table named Department with the attributes Dept_ID, Name, Year_Established, Head_Name, No_of_Employees.
(i) Find the number employees in a CSE department.
(ii) List Name, Dept_ID of all the departments which are established in the year 2010.
 */
public class Q1 {
	public static void main(String args[]) {
		
		String dbDriver = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "";
		String dbUrl= "jdbc:mysql://localhost:3306/see";
		
		
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			
			String q1 = "SELECT No_of_Employees FROM Department WHERE Name=\"CSE\"";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(q1);
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
			
			String q2 = "SELECT Name, Dept_ID FROM Department WHERE Year_Established=2010";
			rs = st.executeQuery(q2);
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getInt(2));	
			}
			con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
