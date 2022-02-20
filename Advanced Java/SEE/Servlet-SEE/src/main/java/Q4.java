
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Write a java servlet program that loads area and phone no. of police station of that area from 
 * a database. It takes area or phone number as input and prints the corresponding fields. 
 * (Note: Create police_station table with appropriate fields).
 */
@WebServlet("/Q4")
public class Q4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Q4() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		int a = Integer.parseInt(request.getParameter("a"));
		String inp = request.getParameter("inp");
		
		
		Connection con = null;
		System.out.println("Connected to DB");
		try {
			con = see.db.connection();
			
			if(!con.isClosed()) {
				System.out.println("Connected to DB");
			} else {
				System.out.println("Couldn't connect to DB");
			}
			Statement st = con.createStatement();
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			if(a==1) {
				String q = "SELECT * FROM police WHERE area=\""+ inp + "\"";
				ResultSet rs = st.executeQuery(q);
				while(rs.next()) {
					out.println(inp + " " + rs.getInt(1));
				}
			}
			else {
				String q = "SELECT * FROM police WHERE phone=\""+ inp + "\"";
				ResultSet rs = st.executeQuery(q);
				while(rs.next()) {
					out.println(inp + " " + rs.getString(1));
				}	
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
