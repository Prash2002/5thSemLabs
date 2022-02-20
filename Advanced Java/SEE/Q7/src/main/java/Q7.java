import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Write a servlet program that uses JDBC to display the subjects allotted for the faculty. 
 * Subjects Table should have Sub_ID, Sub_Name, Faculty_ID as the fields. Update subject 
 * details for a faculty and display how many rows are updated.
 */
@WebServlet("/Q7")
public class Q7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Q7() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		int sub_id = Integer.parseInt(request.getParameter("sub_id"));
		int fac_id = Integer.parseInt(request.getParameter("fac_id"));
		String sub_name = request.getParameter("sub_name");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/see", "root", "");
			
			String q = "UPDATE subjects SET `sub_id`=? , `sub_name`=? WHERE Faculty_ID= ? ";
			
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1, sub_id);
			ps.setString(2, sub_name);
			ps.setInt(3, fac_id);
			ps.execute();
			
			q = "SELECT * FROM subjects";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(q);
			PrintWriter out = response.getWriter();
			while(rs.next()) {
				out.println("<h2>"+ rs.getInt(1) + " "+ rs.getString(2)+ " "+ rs.getInt(3)+ "</h2>");
			}						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
