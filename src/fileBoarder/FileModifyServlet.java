package fileBoarder;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileModifyServlet")
public class FileModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileModifyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");

		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("upload");	// 서버상 경로.

		System.out.println("경로 : "+ path);

		// 파일 올리는 작업
		MultipartRequest multi = 
				new MultipartRequest(
						request, 
						path, //저장 위치
						8 * 1024 * 1024, // 바이트 단위 저장 용량
						"UTF-8", 
						new DefaultFileRenamePolicy());	// 같은 이름 파일이 있을 수 있으니 방지
		
		Enumeration en = multi.getFileNames();
		String author = multi.getParameter("author");
		String title = multi.getParameter("title");
		String num = multi.getParameter("num");
		String fileN = null;
		
		while(en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String fileName = multi.getFilesystemName(name);
			fileN = fileName;
			System.out.println("name : " + name + ", fileName : " + fileName);
		}
		
		// 입력한 뒤 저장된 값 가져오기.
		FileDAO dao = new FileDAO();
		FileVO vo = new FileVO();	// 여기에 정보가 담겨야 한다.

		vo.setAuthor(author);
		vo.setFileName(fileN);
		vo.setTitle(title);
		vo.setNum(Integer.parseInt(num));
		
		//가져온 값을 json 형식으로 수정. {"retCode":"Fail"}
		JSONObject obj = new JSONObject();
		
		if(dao.updateFile(vo)) {
			obj.put("retCode", "Success");
		} else {
			obj.put("retCode", "Fail");
		}
		
		response.getWriter().print(obj);
	}

}
