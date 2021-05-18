package book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String bc = request.getParameter("bCode");
		
		BookVO vo = new BookVO();
		vo.setBookCode(Integer.parseInt(bc));
		
		BookDAO dao = new BookDAO();
		int deleteCnt = dao.deleteBook(vo);
		
		System.out.println("삭제 결과 : " + deleteCnt);
		response.getWriter().print(deleteCnt);
		
	}

}
