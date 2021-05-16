package cat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allCat")
public class AllCatListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AllCatListServlet() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		JSONArray ary = new JSONArray();
		
		CatDAO dao = new CatDAO();
		List<CatVO> list = dao.allCatList();
		
		for(CatVO vo : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("cSlave", vo.getCatSlave());
			obj.put("cName", vo.getCatName());
			obj.put("cSpecies", vo.getCatSpecies());
			obj.put("cGender", vo.getCatGender());
			obj.put("cAge", vo.getCatAge());
			
			ary.add(obj);
		}
		
		resp.getWriter().print(ary);
	}
	
}
