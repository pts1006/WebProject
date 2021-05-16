package cat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteCat")
public class DeleteCatServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public DeleteCatServlet () {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		resp.setCharacterEncoding("UTF-8");
		
		String cSlave = req.getParameter("cSlave");
		
		CatVO vo = new CatVO();
		vo.setCatSlave(cSlave);
		
		System.out.println("delete data : " + vo.toString());
		
		CatDAO dao = new CatDAO();
		int deleteCnt = dao.deleteCat(vo);
		
		System.out.println("delete query result : " + deleteCnt);
		resp.getWriter().print(deleteCnt);
		
	}
}
