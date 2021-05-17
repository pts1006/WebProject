package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteSong")
public class DeleteSongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteSongServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sCode = request.getParameter("songCode");
		
		SongVO vo = new SongVO();
		vo.setSongCode(sCode);
		
		System.out.println("삭제 값 : " + vo.toString());
		
		SongDAO dao = new SongDAO();
		int deleteCnt = dao.deleteSong(vo);
		
		System.out.println("삭제 결과 : " + deleteCnt);
		response.getWriter().print(deleteCnt);
	}

}
