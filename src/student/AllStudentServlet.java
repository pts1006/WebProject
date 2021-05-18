package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/allStudent")
public class AllStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AllStudentServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		JSONArray ary = new JSONArray();
		
		StudentDAO dao = new StudentDAO();
		List<StudentVO> list = dao.allStudent();
		
		for(StudentVO vo : list ) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("studentCode", vo.getStudentCode());
			obj.put("studentName", vo.getStudentName());
			obj.put("studentGrade", vo.getStudentGrade());
			obj.put("studentClass", vo.getStudentClass());
			obj.put("studentGender", vo.getStudentGender());
			
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
