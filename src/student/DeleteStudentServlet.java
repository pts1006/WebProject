package student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteStudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String co = request.getParameter("studentCode");
		
		StudentVO vo = new StudentVO();
		vo.setStudentCode(Integer.parseInt(co));
		
		System.out.println("삭제될 값 : " + vo.toString());
		
		StudentDAO dao = new StudentDAO();
		int resultCnt = dao.deleteStudent(vo);
		
		System.out.println("삭제 쿼리 결과 : " + resultCnt);
		
		response.getWriter().print(resultCnt);
	}

}
