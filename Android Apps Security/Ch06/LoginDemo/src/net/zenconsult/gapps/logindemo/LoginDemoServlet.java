package net.zenconsult.gapps.logindemo;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class LoginDemoServlet extends HttpServlet {
	private String username = "sheran";
	private String password = "s3kr3tc0dez"; // Hardcoded here intended to
												// simulate a database fetch
	private static final Logger log = Logger.getLogger(LoginDemoServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String user = req.getParameter("username").trim(); // No user input validation
													// here!
		String pass = req.getParameter("password").trim(); // No user input validation
		log.info("Username: "+user+" Password: "+pass);											// here!

		resp.setContentType("text/plain");
		if (user.equals(username) && pass.equals(password)) {
			resp.getWriter().println("Login success!!");
		} else {
			resp.getWriter().println("Login failed!!");
		}

	}
}
