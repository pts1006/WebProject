package book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertBook")
public class InsertBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InsertBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		String bc = request.getParameter("bCode");
		String bt = request.getParameter("bTitle");
		String au = request.getParameter("author");
		String gen = request.getParameter("genre");
		
		BookVO vo = new BookVO();
		vo.setBookCode(Integer.parseInt(bc));
		vo.setBookTitle(bt);
		vo.setAuthor(au);
		vo.setGenre(gen);
		
		BookDAO dao = new BookDAO();
		int insertCnt = dao.insertBook(vo);
		
		System.out.println("입력 결과 : " + insertCnt);
		response.getWriter().print(insertCnt);
	}

}
