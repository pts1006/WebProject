package book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allBook")
public class AllBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public AllBookServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray ary = new JSONArray();
		
		BookDAO dao = new BookDAO();
		List<BookVO> list = dao.allBook();
		
		for(BookVO vo : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("bCode", vo.getBookCode());
			obj.put("bTitle", vo.getBookTitle());
			obj.put("author", vo.getAuthor());
			obj.put("genre", vo.getGenre());
			
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
