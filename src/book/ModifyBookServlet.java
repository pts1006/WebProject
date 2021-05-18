package book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifyBook")
public class ModifyBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ModifyBookServlet() {
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
		int modifyCnt = dao.modifyBook(vo);
		
		System.out.println("수정 결과 : " + modifyCnt);
		response.getWriter().print(modifyCnt);
	}

}
