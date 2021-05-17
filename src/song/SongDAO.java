package song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
	
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

	public List<SongVO> allSongList() {
		
		conn = DBCon.getConnect();
		
		String sql = "select * from exam_song";
		
		List<SongVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				SongVO vo = new SongVO();
				vo.setSongCode(rs.getString("song_code"));
				vo.setSongTitle(rs.getString("song_title"));
				vo.setVocal(rs.getString("vocal"));
				vo.setComposer(rs.getString("composer"));
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

	public int deleteSong(SongVO vo) {
		
		conn = DBCon.getConnect();
		
		String sql = "delete from exam_song where song_code =?";
		
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getSongCode());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("삭제ㅇㅇ");
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

	public int modifySong(SongVO vo) {
		
		conn = DBCon.getConnect();
		
		int result = 0;
		
		String sql = "update exam_song "
				+ "set "
				+ "song_title =?, "
				+ "vocal = ?, "
				+ "composer = ?, "
				+ "genre = ? "
				+ "where song_code = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getSongTitle());
			psmt.setString(2, vo.getVocal());
			psmt.setString(3, vo.getComposer());
			psmt.setString(4, vo.getGenre());
			psmt.setString(5, vo.getSongCode());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("수정ㅇㅇ");
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

	public int insertSong(SongVO vo) {
		
		conn = DBCon.getConnect();
		
		int result = 0;
		
		String sql = "insert into exam_song values (?, ?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getSongCode());
			psmt.setString(2, vo.getSongTitle());
			psmt.setString(3,vo.getVocal());
			psmt.setString(4, vo.getComposer());
			psmt.setString(5, vo.getGenre());
			
			result = psmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("입력ㅇㅇ");
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

}
