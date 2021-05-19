package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public void close() {

		try {
			if (rs != null) rs.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<StudentVO> allStudent(){
		
		conn = DBCon.connect();
		
		String sql = "select * from exam_student";
		
		List<StudentVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setStudentCode(rs.getInt("student_code"));
				vo.setStudentName(rs.getString("student_name"));
				vo.setStudentGrade(rs.getInt("student_grade"));
				vo.setStudentClass(rs.getInt("student_class"));
				vo.setStudentGender(rs.getString("student_gender"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public int insertStudent(StudentVO vo) {
		
		conn = DBCon.connect();
		
		int result = 0;
		
		String sql = "insert into exam_student values(?, ?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, vo.getStudentCode());
			psmt.setString(2, vo.getStudentName());
			psmt.setInt(3, vo.getStudentGrade());
			psmt.setInt(4, vo.getStudentClass());
			psmt.setString(5, vo.getStudentGender());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 입력");
			} else {
				System.out.println("입력 ㄴㄴ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	public int updateStudent(StudentVO vo) {
		
		conn = DBCon.connect();
		
		int result = 0;
		
		String sql = "update exam_student set student_name = ?, student_grade = ?, student_class = ?, student_gender = ? where student_code =?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getStudentName());
			psmt.setInt(2, vo.getStudentGrade());
			psmt.setInt(3, vo.getStudentClass());
			psmt.setString(4, vo.getStudentGender());
			psmt.setInt(5, vo.getStudentCode());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 업뎃");
			} else {
				System.out.println("업뎃 ㄴㄴ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	public int deleteStudent(StudentVO vo) {
		
		conn = DBCon.connect();
		
		int result = 0;
		
		String sql = "delete from exam_student where student_code = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getStudentCode());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 삭제");
			} else {
				System.out.println("삭제 ㄴㄴ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
}
