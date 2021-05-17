package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
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
	
	public List<BookVO> allBook() {
		
		conn = DBCon.getConnect();
		
		String sql = "select * from exam_book";
		
		List<BookVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookCode(rs.getInt("book_code"));
				vo.setBookTitle(rs.getString("book_title"));
				vo.setAuthor(rs.getString("author"));
				vo.setGenre(rs.getString("genre"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}

	public int insertBook(BookVO vo) {
		
		int result = 0;
		conn = DBCon.getConnect();
		
		String sql = "insert into exam_book values(?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1,vo.getBookCode());
			psmt.setString(2, vo.getBookTitle());
			psmt.setString(3, vo.getAuthor());
			psmt.setString(4, vo.getGenre());
			
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

	public int deleteBook(BookVO vo) {
		
		int result = 0;
		conn = DBCon.getConnect();
		
		String sql = "delete from exam_book where book_code = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,vo.getBookCode());
			
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

	public int modifyBook(BookVO vo) {
		
		int result = 0;
		conn = DBCon.getConnect();
		
		String sql = "update exam_book "
				+ "set "
				+ "book_title = ?, "
				+ "author = ?, "
				+ "genre = ? "
				+ "where book_code = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getBookTitle());
			psmt.setString(2, vo.getAuthor());
			psmt.setString(3, vo.getGenre());
			psmt.setInt(4,vo.getBookCode());
			
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

}
