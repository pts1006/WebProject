package fileBoarder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/getFileServlet")
public class GetFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFileServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		// 한글 패치.
		
		String num = request.getParameter("num");
		int fileNum = Integer.parseInt(num);
		
		FileDAO dao = new FileDAO();
		
		FileVO vo = dao.getFile(fileNum);
		// getFile()의 인자로 들어올 값은 int값이어야 해서 fileNum라는 변수를 새로 만들었음.
		
		JSONObject obj = new JSONObject();
		obj.put("num", vo.getNum());
		obj.put("title", vo.getTitle());
		obj.put("author", vo.getAuthor());
		obj.put("fileName", vo.getFileName());
		obj.put("day", vo.getDay());
		
		response.getWriter().print(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
