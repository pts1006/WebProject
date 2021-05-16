package cat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifyCat")
public class ModifyCatServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ModifyCatServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		CatVO vo = new CatVO();
		
		String cName = req.getParameter("cName");
		String cSpecies = req.getParameter("cSpecies");
		String cGender = req.getParameter("cGender");
		String cAge = req.getParameter("cAge");
		String cSlave = req.getParameter("cSlave");
		
		vo.setCatName(cName);
		vo.setCatSpecies(cSpecies);
		vo.setCatGender(cGender);
		vo.setCatAge(Integer.parseInt(cAge));
		vo.setCatSlave(cSlave);
		
		System.out.println("modify data : " + vo.toString());
		
		CatDAO dao = new CatDAO();
		int modiCnt = dao.updateCat(vo);
		
		System.out.println("modi query result : " + modiCnt);
		
		if(modiCnt > 0) {
			resp.getWriter().print(modiCnt);
		} else {
			resp.sendError(404, "에러입니다.");	// 기능하지 않음.
		}
	}
	
}
