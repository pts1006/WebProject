package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;

	// 닫는 메소드 생성
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Employee insertEmpByseq(Employee emp) {

		conn = DBCon.getConnect();

		Employee empl = new Employee(); // 입력된 정보를 반환해 주기 위해 만들었음.

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "INSERT INTO emp_temp(employee_id, first_name, last_name, job_id, email, hire_date, salary, department_ID) "
				+ "values(?, ?, ?, ?, ?, ?, ?, 50)";

		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);
			}

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getJobId());
			psmt.setString(5, emp.getEmail());
			psmt.setString(6, emp.getHireDate());
			psmt.setInt(7, emp.getSalary());

			int r = psmt.executeUpdate(); // 처리한 건수 집어 넣는 오브젝트. 확인용
			System.out.println(r + "건 입력 됨.");

			// 입력한 값을 그대로 반환해 주기 위해서.
			empl.setEmployeeId(empId);
			empl.setFirstName(emp.getFirstName());
			empl.setLastName(emp.getLastName());
			empl.setEmail(emp.getEmail());
			empl.setJobId(emp.getJobId());
			empl.setHireDate(emp.getHireDate());
			empl.setSalary(emp.getSalary());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empl;
	}

	public void insertEmp(Employee emp) {
		String sql = "INSERT INTO emp_temp(employee_id, first_name, last_name, job_id, email, hire_date, salary, department_id)"
				+ "values((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?, ?, ?, 50)";
		// 해당 sql은 'select max ~~', 이 구문이, 커밋하냐 안 하냐에 따라 최대값의 기준이 달라서 좋은 방법은 아님.
		// (동시에 발생할 경우 최대값이 같아서 중첩되는 현상 발생할 수 있음)
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getJobId());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setInt(6, emp.getSalary());

			int r = psmt.executeUpdate(); // 처리한 건수 집어 넣음
			System.out.println(r + "건 입력 됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Employee> getEmpByDept(String dept) {
		// 사원 정보 가지고 오는 처리 1
		String sql = "select * from emp_temp where department_id = " + dept //
				+ " order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setJobId(rs.getString("job_Id"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return employees;
	}

	public List<Employee> getEmpList() {
		// 사원 정보 가지고 오는 처리 2
		String sql = "select * from emp_temp order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return employees;
	}

	// empl_demo
	public List<Employee> getEmployeeList() {
		// 사원 정보 가지고 오는 처리 2
		String sql = "select * from empl_demo order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return employees;
	}

	// getEmployeeList()
	public Map<String, Integer> getEmployeeByDept() {

		// 부서명, 사원수
		Map<String, Integer> map = new HashMap<>();

		String sql = "SELECT department_name, COUNT(1)\r\n" + "FROM empl_demo e, departments d\r\n"
				+ "WHERE e.department_id = d.department_id\r\n" + "GROUP BY department_name";
		conn = DBCon.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2)); // map.put("부서", 20);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}

	// 스케쥴 정보를 가지고 오는 메소드.
	public List<ScheduleVO> getScheduleList() {
		conn = DBCon.getConnect();

		String sql = "select * from schedule";
		List<ScheduleVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ScheduleVO vo = new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_day"));
				vo.setEndDay(rs.getString("end_day"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 한 건 입력
	public void insertSchedule(ScheduleVO vo) {

		conn = DBCon.getConnect();

		String sql = "insert into schedule(title, start_day, end_day) values(?, ?, ?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());

			int r = psmt.executeUpdate(); // 처리한 건수 집어 넣음
			System.out.println(r + "건 입력 됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	// 한 건 삭제
	public void deleteScedule(ScheduleVO vo) {

		conn = DBCon.getConnect();

		String sql = "delete from schedule where title = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());

			int r = psmt.executeUpdate();
			if (r == 1) {
				System.out.println(r + "건 삭제.");
			} else {
				System.out.println("삭제 안 됨.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
