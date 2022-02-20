

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetCookies")
public class SetCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetCookies() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		for(int i=1; i<=3; i++) {
			String no = "Type1-CookieNo-" + i;
			String val = "Type1-CookieVal-" + i;
			Cookie ck = new Cookie(no, val);
			
			response.addCookie(ck);
			
			no = "Type2-CookieNo-" +i;
			val = "Type2-CookieVal-" + i;
			ck = new Cookie(no, val);
			ck.setMaxAge(100);
			
			response.addCookie(ck);
			
		}
		out.println("Cookies set");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
