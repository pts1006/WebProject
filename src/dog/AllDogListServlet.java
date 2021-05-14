package dog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allDogListServlet")
public class AllDogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllDogListServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		JSONArray ary = new JSONArray();
		DogDAO dao = new DogDAO();
		List<DogVO> list = dao.allDogList();
		
		for(DogVO vo : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("dName", vo.getPetName());
			obj.put("species", vo.getSpecies());
			obj.put("age", vo.getAge());
			obj.put("dColor", vo.getColor());
			obj.put("dSize", vo.getBodySize());
			
			ary.add(obj);
			
		}
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
