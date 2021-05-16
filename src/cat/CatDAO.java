package cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatDAO {

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
	
	//전체 리스트 뽑기
	public List<CatVO> allCatList(){
		
		conn = DBCon.getConnect();
		
		String sql = "select * from exam_cat";
		
		List<CatVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CatVO vo = new CatVO();
				vo.setCatName(rs.getString("cat_name"));
				vo.setCatSpecies(rs.getString("cat_species"));
				vo.setCatGender(rs.getString("cat_gender"));
				vo.setCatAge(rs.getInt("cat_age"));
				vo.setCatSlave(rs.getString("cat_slave"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	public int insertCat(CatVO vo) {
		
		conn = DBCon.getConnect();
		
		int result = 0;
		
		String sql = "insert into exam_cat values(?, ?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getCatName());
			psmt.setString(2, vo.getCatSpecies());
			psmt.setString(3, vo.getCatGender());
			psmt.setInt(4, vo.getCatAge());
			psmt.setString(5, vo.getCatSlave());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 입력.");
			} else {
				System.out.println("입력 실패.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	
	public int updateCat(CatVO vo) {
		
		conn = DBCon.getConnect();
		
		int result = 0;
		
		String sql = "update exam_cat "
				+ "set "
				+ "cat_name = ?, "
				+ "cat_species = ?, "
				+ "cat_gender = ?, "
				+ "cat_age = ? "
				+ "where cat_slave = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getCatName());
			psmt.setString(2, vo.getCatSpecies());
			psmt.setString(3, vo.getCatGender());
			psmt.setInt(4, vo.getCatAge());
			psmt.setString(5, vo.getCatSlave());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 수정.");
			} else {
				System.out.println("수정 실패.");	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	public int deleteCat(CatVO vo) {
		
		conn = DBCon.getConnect();
		
		String sql = "delete from exam_cat where cat_slave = ?";
		
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCatSlave());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println(result + "건 삭제.");
			} else {
				System.out.println("삭제 실패.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
}
