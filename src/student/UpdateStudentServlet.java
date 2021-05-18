package student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateStudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		String co = request.getParameter("studentCode");
		String na = request.getParameter("studentName");
		String gr = request.getParameter("studentGrade");
		String cl = request.getParameter("studentClass");
		String ge = request.getParameter("studentGender");
		
		StudentVO vo = new StudentVO();
		vo.setStudentCode(Integer.parseInt(co));
		vo.setStudentName(na);
		vo.setStudentGrade(Integer.parseInt(gr));
		vo.setStudentClass(Integer.parseInt(cl));
		vo.setStudentGender(ge);
		
		System.out.println("수정할 값 : " + vo.toString());
		
		StudentDAO dao = new StudentDAO();
		int resultCnt = dao.updateStudent(vo);
		
		System.out.println("수정된 수 : " + resultCnt);
		
		response.getWriter().print(resultCnt);
		
	}

}
