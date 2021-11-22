

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
/**
 * Servlet implementation class Q3
 */
@WebServlet("/Q3")
public class Q3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Q3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String fullname = request.getParameter("fullname");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = "";
		String[] n = fullname.trim().split("\s");
		for(String i: n) {
			name += i.charAt(0);
		}
		name = name.toUpperCase();
		out.print(name);
		
	}

}
