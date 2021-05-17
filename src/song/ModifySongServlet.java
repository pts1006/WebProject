package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifySong")
public class ModifySongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ModifySongServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		String sc = request.getParameter("songCode");
		String st = request.getParameter("songTitle");
		String voc = request.getParameter("vocal");
		String comp = request.getParameter("composer");
		String gen = request.getParameter("genre");
		
		SongVO vo = new SongVO();
		vo.setSongCode(sc);
		vo.setSongTitle(st);
		vo.setVocal(voc);
		vo.setComposer(comp);
		vo.setGenre(gen);
		
		System.out.println("수정 데이터 : " + vo.toString());
		
		SongDAO dao = new SongDAO();
		int modiCnt = dao.modifySong(vo);
		
		System.out.println("수정 결과 : " + modiCnt);
		
		response.getWriter().print(modiCnt);
	}

}
