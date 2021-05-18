package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertSong")
public class InsertSongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InsertSongServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 시간나면 '음수 안 들어가게', 그리고 '같은 값 넣었을 때 경고창 뜨게' 만들기
		
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
		
		System.out.println("삽입 데이터 : " + vo.toString());
		
		SongDAO dao = new SongDAO();
		int insertCnt = dao.insertSong(vo);
		
		System.out.println("삽입 결과 : " + insertCnt);
		
		response.sendRedirect("/WebProject/servletAlone/exer_alone_4.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
