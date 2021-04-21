package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;

	public Employee insertEmpByseq(Employee emp) {
		
		conn = DBCon.getConnect();
		
		Employee empl = new Employee();	// 입력된 정보를 반환해 주기 위해 만들었음.
		
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "INSERT INTO emp_temp(employee_id, last_name, email, hire_date, job_id) "
				+ "values(?, ?, ?, ?, ?)";

		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);
			}

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			
			int r = psmt.executeUpdate(); // 처리한 건수 집어 넣는 오브젝트. 확인용
			System.out.println(r + "건 입력 됨.");
			
			//입력한 값을 그대로 반환해 주기 위해서.
			empl.setEmployeeId(empId);
			empl.setEmail(emp.getEmail());
			empl.setLastName(emp.getLastName());
			empl.setJobId(emp.getJobId());
			empl.setHireDate(emp.getHireDate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empl;
	}

	public void insertEmp(Employee emp) {
		String sql = "INSERT INTO emp_temp(employee_id, last_name, email, hire_date, job_id) "
				+ "values((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?)";
		// 물음표는 나중에 parameter로 입력하겠다는 의도.
		// 해당 sql은 'select max ~~', 이 구문이, 커밋하냐 안 하냐에 따라 최대값의 기준이 달라서 좋은 방법은 아님. (동시에
		// 발생할 경우 최대값이 같아서 중첩되는 현상 발생할 수 있음)
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());

			int r = psmt.executeUpdate(); // 처리한 건수 집어 넣음
			System.out.println(r + "건 입력 됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	}

	public List<Employee> getEmpByDept(String dept) {
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
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return employees;
	}

	public List<Employee> getEmpList() {

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
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return employees;
	}
}
