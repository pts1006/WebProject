package cat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertCat")
public class InsertCatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public InsertCatServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String cName = req.getParameter("cName");
		String cSpecies = req.getParameter("cSpecies");
		String cGender = req.getParameter("cGender");
		String cAge = req.getParameter("cAge");
		String cSlave = req.getParameter("cSlave");
		
		CatVO vo = new CatVO();
		vo.setCatName(cName);
		vo.setCatSpecies(cSpecies);
		vo.setCatGender(cGender);
		vo.setCatAge(Integer.parseInt(cAge));
		vo.setCatSlave(cSlave);
		
		System.out.println("insert result : " + vo.toString());
		
		CatDAO dao = new CatDAO();
		int insertCnt = dao.insertCat(vo);
		
		System.out.println("insert query result : " + insertCnt);
		
		if(insertCnt != 0) {
			resp.getWriter().print(insertCnt);
		} else {
			resp.sendError(302, "중복된 값이 있습니다.");	// 기능하지 않음.
		}
		
	}
}
