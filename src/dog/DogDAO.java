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
				
				list.add(vo);
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
		// insert 는 순서대로 된다.
		// 순서대로 안되고 원하는 update 처럼 where 절 하고 싶으면 그렇게 하는방법을
		// 검색해서 할것 ? ,? ,? ,? 는 사실상 디비에서 첨만든 위부터 아래 오름차순임
		// asc(오름차순), desc(내림차순) <<
		String sql = "insert into exam_board values(?, ?, ?, ?, ?)";
		
		/*
		pmst = ~~~ sql 문 넣었어~
		
		PreparedStatement psmt = new PreparedStatement();
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, vo.getSpecies());
		
		*/
		
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, vo.getSpecies());
			psmt.setString(2, vo.getPetName());
			psmt.setString(3, vo.getAge());
			psmt.setString(4, vo.getColor());
			psmt.setString(5, vo.getBodySize());

			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 입력.");
			} else {
				System.out.println("입력 안 됨.");
			}
			
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
			
			result = psmt.executeUpdate();
			
			System.out.println(result + "건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
}
