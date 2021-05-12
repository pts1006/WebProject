package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	'jq_ajax_3.html'과 'jq+ajax_4.html' 파일의 연장선
	post와 get 비교
*/
@WebServlet("/ajax.html")
public class AjaxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doGet
		String p1 = req.getParameter("p1");
		String p2 = req.getParameter("p2");
		resp.getWriter().print("<h1>Ajax Get Page</h1>");
		resp.getWriter().print("<h3>" + p1 + " " + p2 + "</h3>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doPost
		resp.getWriter().print("<h1>Ajax Post Page</h1>");
	}
}
