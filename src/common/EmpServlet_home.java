package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/empList.do") // 생성할 때만 '/'를 쓰고 내부나 다른 곳에서 불러올 때는 쓰면 안 된다.
public class EmpServlet_home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp); // doGet 방식 요청. Empservlet에 2개의 메서드 호출됨
		PrintWriter out = resp.getWriter(); // out 변수는 파일stream .

		String dept = req.getParameter("dept");

//		EmpDAO dao = new EmpDAO();
//
//		List<Employee> list = dao.getEmpList();
//
//		if (dept == null) {
//			list = dao.getEmpList();
//		} else {
//			list = dao.getEmpByDept(dept);
//		}
//
//		String jsonData = "[";
//		// [ {"empId":"?", "fName":"?", "IName":"?"}, ... ]
//		int cnt = 0;
//		for (Employee emp : list) {
//			jsonData += "{\"empId\":\"" + emp.getEmployeeId()
//					+ "\", \"fName\":\"" + emp.getFirstName()
//					+ "\", \"lName\":\"" + emp.getLastName()
//					+ "\", \"jobId\":\"" + emp.getJobId()
//					+ "\", \"email\":\"" + emp.getEmail()
//					+ "\", \"hire_date\":\"" + emp.getHireDate()
//					+ "\", \"salary\":\""+ emp.getSalary() + "\"}";
//			if (++cnt == list.size()) {
//				continue;
//			}
//			jsonData += ",";
//		}
//
//		jsonData += "]";
		int[] salarys = {50000, 150000, 2000000, 5000, 30000, 1500};
		String jsonData = "[";
		for (int i = 0; i < 5; i++) {
			jsonData += "{\"employee_id\" :\"" + "generateId"+i + "\", "
					+ "\"first_name\" :\"" + "generateFirstName"+i + "\", "
					+ "\"last_name\" :\"" + "generateLastName"+i + "\", "
					+ "\"job_id\" :\"" + "generateJobId"+i + "\", "
					+ "\"email\" :\"" + "generateEmail"+i + "\", "
					+ "\"hire_date\" :\"" + new Date() + "\", "
					+ "\"salary\" :\"" +  salarys[i] + "\""
					+ "}";
			if ( i < 4 ) jsonData += ",";
		}
		

		out.println(jsonData+"]");
	}
// http://localhost : 서버정보 / webpro : 프로젝트 정보 / empList : url 정보

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String hireDate = req.getParameter("hire_date");
		String jobId = req.getParameter("job_id");
		String firstName = req.getParameter("first_name");
		int salary = Integer.parseInt(req.getParameter("salary"));

		// Employee에다 값 쑤셔넣기.
		Employee emp = new Employee();
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setHireDate(hireDate);
		emp.setJobId(jobId);
		emp.setFirstName(firstName);
		emp.setSalary(salary);

//		EmpDAO dao = new EmpDAO();
//		Employee empl = dao.insertEmpByseq(emp);
		//{"eid" : "?", "fname" : "?"....}
		
		PrintWriter out = resp.getWriter();
		String jsonData = "[";
		jsonData += "{\"employee_id\" :\"" + emp.getEmployeeId() + "\", "
				+ "\"first_name\" :\"" + emp.getFirstName() + "\", "
				+ "\"last_name\" :\"" + emp.getLastName() + "\", "
				+ "\"job_id\" :\"" + emp.getJobId() + "\", "
				+ "\"email\" :\"" + emp.getEmail() + "\", "
				+ "\"hire_date\" :\"" + emp.getHireDate() + "\", "
				+ "\"salary\" :\"" + emp.getSalary() + "\""
				+ "}";
		out.print(jsonData+"]");
	}
}
