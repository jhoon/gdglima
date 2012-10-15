package pe.gdglima.devfestlima.engine;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class DevFestLimaServiceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("Servicios GDG DevFestLima <br>");
		resp.getWriter().println("  <a href=\"https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/list?limit=10\"> https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/list?limit=10 </a><br>");
		resp.getWriter().println("  <a href=\"https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/search/{userid}\"> https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/search/{userid}</a><br>");
		resp.getWriter().println("  <a href=\"https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/get/{userid}\"> https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/get/{userid}</a><br>");
		resp.getWriter().println("  <a href=\"https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/insert\"> https://gdgdevfestlima.appspot.com/_ah/api/speakers/v1/speakers/insert</a><br>");
		
		
		
	}
}
