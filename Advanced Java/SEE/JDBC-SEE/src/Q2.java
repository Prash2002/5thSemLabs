/* Write a JAVA-JDBC program that connects to the database College with Student table.
 *  Assume appropriate attributes for the Student table. Write a program to display the details of 
 *  those Students who have CGPA less than 9. Also update the Student table to change the CGPA of 
 *  student named “John” from 8.50 to 9.4 using updatable result set. Finally display the results and
 *   disconnect from the database.
 */

import java.sql.*;
import java.util.Scanner;

public class Q2 {
	public static void main(String args[]) {
		String driver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/see";
		String username = "root";
		String password = "";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			
			PreparedStatement st1 = con.prepareStatement("INSERT INTO student values (?,?,?)");
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Enter name, cgpa");
                String name = sc.next();
                String dept = sc.next();
                double cgpa = sc.nextDouble();

                st1.setString(1, name);
                st1.setString(2, dept);
                st1.setDouble(3, cgpa);

                st1.execute();

                System.out.println("Do you want to add more records? y/n");
                String c = sc.next();
                if (c.startsWith("n")) {
                    break;
                }
            } while (true);
			
			
			Statement st = con.createStatement();
			String q1 = "SELECT * FROM student WHERE CGPA < 9.0";
			ResultSet rs = st.executeQuery(q1);
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2) + " "+rs.getFloat(3));
			}
			
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String q2 = "SELECT * FROM student";
			rs = st.executeQuery(q2);
			System.out.println("Updating... ");
			while(rs.next()) {
				if(rs.getString(1).equals("John") && rs.getFloat(3)==8.5) {
					rs.updateFloat(3, (float) 9.40);
					rs.updateRow();
				}
				System.out.println(rs.getString(1)+" "+ rs.getString(2) + " "+rs.getFloat(3));
			}
			
			
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
