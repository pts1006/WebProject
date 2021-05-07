package userTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCon;

public class UserDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (psmt != null)
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public List<UserVO> getUserList(){
		
		conn = DBCon.getConnect();
		List<UserVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from user_temp");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
		
	}
	
	public UserVO getInsert(UserVO vo) {
		
		conn = DBCon.getConnect();
		
		String sql = "insert into user_temp values(?, ?, ?, ?, ?)";
		
		UserVO user = new UserVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getUserID());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getUserPass());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getGender());
			
			int r = psmt.executeUpdate();
			if( r != 0 ) {
				System.out.println( r + "건 입력.");
			} else {
				System.out.println("입력 안 됨.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return user;
	}
	
}
