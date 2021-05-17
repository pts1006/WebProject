package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifyUser")
public class ModifyUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ModifyUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		InfoVO vo = new InfoVO();
		
		String uID = request.getParameter("uId");
		String uName = request.getParameter("uName");
		String species = request.getParameter("species");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		
		vo.setUserId(Integer.parseInt(uID));
		vo.setUserName(uName);
		vo.setUserSpecies(species);
		vo.setUserGender(gender);
		vo.setUserAge(Integer.parseInt(age));
		
		System.out.println("modify data : " + vo.toString());
		
		InfoDAO dao = new InfoDAO();
		int modiCnt = dao.modifyUser(vo);
		
		System.out.println("modi query result :" + modiCnt);

		response.getWriter().print(modiCnt);

	}

}
