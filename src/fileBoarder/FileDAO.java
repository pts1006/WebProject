package fileBoarder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCon;

/*
	/WebProject/WebContent/jquery/upload.html 시리즈.
*/

public class FileDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public void deleteFile(FileVO file) {

		conn = DBCon.getConnect();

		String sql = "delete from file_board where num = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, file.getNum());
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

	public FileVO getFile(int num) { // num 값으로 한 건 조회.

		conn = DBCon.getConnect();
		String sql = "select * from file_board where num =?";

		FileVO file = new FileVO();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("file_name"));
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return file;
	}

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

	public List<FileVO> getFileList() {

		conn = DBCon.getConnect();
		List<FileVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from file_board");
			rs = psmt.executeQuery();
			while (rs.next()) {
				FileVO vo = new FileVO();

				vo.setAuthor(rs.getString("author"));
				vo.setDay(rs.getString("day"));
				vo.setFileName(rs.getString("file_name"));
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public FileVO getInsertKeyVal(FileVO vo) {

		conn = DBCon.getConnect();

		// 신규번호, 한 건 입력, 한 건 조회
		String selectKey = "select nvl(max(num),0) + 1 from file_board"; // null일 경우 0을 부여하고 1을 더해서 값을 만들겠다.
		String insertSql = "insert into file_board values(?, ?, ?, ?, to_char(sysdate,'YYYY-MM-DD'))";
		String selectSql = "select * from file_board where num = ?";

		FileVO file = new FileVO(); // 리턴에 담기용
		int key = 0;

		try {
			// 키 값을 가져오는 부분
			psmt = conn.prepareStatement(selectKey);
			rs = psmt.executeQuery();
			if (rs.next()) {
				key = rs.getInt(1);
			}

			// 새로운 키 값을 이용해 한 건 입력.
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, key); // 바로 위에서 담은 key 값을 여기서 씀
			psmt.setString(2, vo.getAuthor());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getFileName());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");

			// 신규 입력된 전체 정보를 가져오기.
			psmt = conn.prepareStatement(selectSql);
			psmt.setInt(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("file_name"));
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return file;
	}
	
	public boolean updateFile (FileVO vo) {
		
		conn = DBCon.getConnect();
		int cnt = 0;
		String sql = "update file_board "
				+ "set author = ?,"
				+ 		"title = ?,"
				+ 		"file_name = ? "
				+ "where num = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAuthor());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getFileName());
			psmt.setInt(4, vo.getNum());
			
			cnt = psmt.executeUpdate();
			
			if ( cnt != 0 ) {
				System.out.println("수정됨.");
			} else {
				System.out.println("수정 안 됨.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt == 0 ? false : true;
	}
}
