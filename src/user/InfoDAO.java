package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDAO {

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

	// 전체 조회
	public List<InfoVO> allUserList(){
		
		conn = DBCon.getonnect();
		
		String sql = "select * from exam_info";
		
		List<InfoVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				InfoVO vo = new InfoVO();
				vo.setUserId(rs.getInt("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserSpecies(rs.getString("user_species"));
				vo.setUserGender(rs.getString("user_gender"));
				vo.setUserAge(rs.getInt("user_age"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 삽입 기능
	public int insertUser(InfoVO vo) {

		conn = DBCon.getonnect();

		String sql = "insert into exam_info values(?, ?, ?, ?, ?)";

		int result = 0;

		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getUserSpecies());
			psmt.setString(4, vo.getUserGender());
			psmt.setInt(5, vo.getUserAge());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 입력.");
			} else {
				System.out.println("ㄴㄴ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	// 수정 기능
	public int modifyUser(InfoVO vo) {

		conn = DBCon.getonnect();

		String sql = "update exam_info set user_name =?, user_species =?, user_gender = ?, user_age =? where user_id = ?";

		int result = 0;

		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getUserName());
			psmt.setString(2, vo.getUserSpecies());
			psmt.setString(3, vo.getUserGender());
			psmt.setInt(4, vo.getUserAge());
			psmt.setInt(5, vo.getUserId());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 수정.");
			} else {
				System.out.println("ㄴㄴ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}
	
	// 삭제 기능
	public int deleteUser(InfoVO vo) {

		conn = DBCon.getonnect();

		String sql = "delete from exam_info where user_id = ?";

		int result = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getUserId());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result +"건 삭제.");
			} else {
				System.out.println("ㄴㄴ.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

}
