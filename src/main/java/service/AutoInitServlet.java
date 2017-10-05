package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AutoInitServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		try {
			TodoApplication.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
