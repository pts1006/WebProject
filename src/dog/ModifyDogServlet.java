package dog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifyDogServlet")
public class ModifyDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyDogServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		DogVO vo = new DogVO();
	
		String dName = request.getParameter("dName");
		String species = request.getParameter("species");
		String age = request.getParameter("age");
		String color = request.getParameter("dColor");
		String dSize = request.getParameter("dSize");
		
		vo.setPetName(dName);
		vo.setSpecies(species);
		vo.setAge(age);
		vo.setColor(color);
		vo.setBodySize(dSize);
		
		System.out.println(vo.toString());
		
		DogDAO dao = new DogDAO();
		int modiResultCnt = dao.updateDog(vo);
		
		System.out.println("modify query result : " + modiResultCnt);
		response.getWriter().print(modiResultCnt);
		
//		response.sendRedirect("/WebProject/servletAlon/exer_alon_1.html");
		
	}

}
