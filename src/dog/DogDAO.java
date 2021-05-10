package dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public void close() {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	
	//전체 리스트 뽑기
	public List<DogVO> allDogList(){
		
		String sql = "select * from exam_board";
		// conn.prepareStatement("select * from exam_board"); 로 바로 선언해도 됨. 단, 앞에 변수 붙여서.
		conn = DBCon.getConnect();
		
		List<DogVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				DogVO vo = new DogVO();
				vo.setPetName(rs.getString("pet_name"));
				vo.setSpecies(rs.getString("species"));
				vo.setAge(rs.getString("age"));
				vo.setColor(rs.getString("color"));
				vo.setBodySize(rs.getString("body_size"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}

	// insert
	public int insertDog(DogVO vo) {
		
		conn = DBCon.getConnect();
		int result = 0;
		String sql = "insert into user_temp values(?, ?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getPetName());
			psmt.setString(2, vo.getSpecies());
			psmt.setString(3, vo.getAge());
			psmt.setString(4, vo.getColor());
			psmt.setString(5, vo.getBodySize());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}
	
	// update
	public int updateDog(DogVO vo) {
		
		conn = DBCon.getConnect();
		int result = 0;
		String sql = "update exam_board "
				+ "set "
				+ "pet_name = ?, "
				+ "species = ?, "
				+ "age = ?, "
				+ "color = ?, "
				+ "body_size = ? "
				+ "where pet_name = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getSpecies());
			psmt.setString(2, vo.getAge());
			psmt.setString(3, vo.getColor());
			psmt.setString(4, vo.getBodySize());
			psmt.setString(5, vo.getPetName());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
}
