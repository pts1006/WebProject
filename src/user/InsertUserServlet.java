package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertUser")
public class InsertUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public InsertUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		String uID = request.getParameter("userId");
		String uName = request.getParameter("userName");
		String species = request.getParameter("species");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		
		InfoVO vo = new InfoVO();
		vo.setUserId(Integer.parseInt(uID));
		vo.setUserName(uName);
		vo.setUserSpecies(species);
		vo.setUserGender(gender);
		vo.setUserAge(Integer.parseInt(age));
		
		System.out.println("insert result : " + vo.toString());
		
		InfoDAO dao = new InfoDAO();
		int insertCnt = dao.insertUser(vo);
		
		System.out.println("insert query result : " + insertCnt);
		
		if(insertCnt != 0) {
			response.sendRedirect("/WebProject/servletAlone/exer_alone_3.html");
//			response.getWriter().print(insertCnt);
		} else {
			response.sendError(302, "중복된 값");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
