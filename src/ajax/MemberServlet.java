package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBCon;

@WebServlet("/jquery/memberServlet.do")	// 실제로 있는 건 아니고, 가상의 주소 같은 느낌. do도 되고 html도 되고, 대체 가능.
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet : 조회를 위함. 디폴트 방식
		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().print("<h2>정상 조회 완료</h2>");
		
		/*
			조회 sql을 작성
			
			조회 결과를 json 형식으로 작성
			[{"id:1, "name": "hong", "age" : 20},
			{"id:2, "name": "hang", "age" : 21},
			{"id:3, "name": "hing", "age" : 22}]
		*/
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doPost : 입력 작업

//		doGet(request, response);	// 위에서 만든 doGet 호출.
		String p1 = request.getParameter("m_id");
		String p2 = request.getParameter("m_name");
		String p3 = request.getParameter("m_age");

		Connection conn = DBCon.getConnect(); // 자바 sql import
		PreparedStatement psmt = null;
		String sql = "insert into member values(?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, p1);
			psmt.setString(2, p2);
			psmt.setString(3, p3);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");

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

		response.getWriter().print("<h2>정상 입력</h2>");

	}

}
