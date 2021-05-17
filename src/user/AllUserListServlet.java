package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allUser")
public class AllUserListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AllUserListServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray ary = new JSONArray();
		
		InfoDAO dao = new InfoDAO();
		List<InfoVO> list = dao.allUserList();
		
		for(InfoVO vo : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("userId", vo.getUserId());
			obj.put("userName", vo.getUserName());
			obj.put("species", vo.getUserSpecies());
			obj.put("gender", vo.getUserGender());
			obj.put("age", vo.getUserAge());
			
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
