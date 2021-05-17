package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allSong")
public class AllSongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AllSongServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray ary = new JSONArray();
		
		SongDAO dao = new SongDAO();
		List<SongVO> list = dao.allSongList();
		
		for(SongVO vo : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("songCode", vo.getSongCode());
			obj.put("songTitle", vo.getSongTitle());
			obj.put("vocal", vo.getVocal());
			obj.put("composer", vo.getComposer());
			obj.put("genre", vo.getGenre());
			
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
