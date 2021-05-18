package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DeleteUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uID = request.getParameter("uId");
		
		InfoVO vo = new InfoVO();
		vo.setUserId(Integer.parseInt(uID));
		
		System.out.println("delete data : " + vo.toString());
		
		InfoDAO dao = new InfoDAO();
		int deleteCnt = dao.deleteUser(vo);
		
		System.out.println("delete query result : " + deleteCnt);
		response.getWriter().print(deleteCnt);
	}

}
