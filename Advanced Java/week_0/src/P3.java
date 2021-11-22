import java.sql.*;
import java.io.*;

class JDBC_prepared_ins_ex {
	public static void main(String args[]) throws Exception {
		System.out.println("MySQL Connect Example.");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "newtest";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Class.forName(driver).getDeclaredConstructor().newInstance();
		conn = DriverManager.getConnection(url + dbName, userName, password);
		System.out.println("Connected to the database");
		String myusn, myname;
		PreparedStatement ps = conn.prepareStatement("insert into w0_3 values(?,?)");
		Statement stmt = conn.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println("enter usn:");
			myusn = br.readLine();
			System.out.println("enter name:");
			myname = br.readLine();

			ps.setString(1, myusn);
			ps.setString(2, myname);

			int i = ps.executeUpdate();
			System.out.println(i + " records added");
			System.out.println("Do you want to continue: y/n");
			String s = br.readLine();
			if (s.startsWith("n")) {
				break;
			}
		} while (true);
		String sql = "SELECT * from w0_3";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("The records are :");
		while (rs.next()) {
			myusn = rs.getString(1);
			myname = rs.getString(2);

			System.out.println(rs.getRow() + "-" + myusn + "  " + myname);
		} // end while
		conn.close();
	}
}
