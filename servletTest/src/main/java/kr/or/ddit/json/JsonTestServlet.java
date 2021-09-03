package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/jsonTestServlet.do")
public class JsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JSON 데이터를 만들어서 응답으로 보내주는 서블릿
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// json으로 응답할때의 ContentType은 text/html;이 아닌 application/json; 으로 바뀐다
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String choice = request.getParameter("choice");
		
		Gson gson = new Gson();
		// json 데이터로 변경된 데이터가 저장될 변수
		String jsonData = null;
		
		switch(choice){
			case "str":  //문자열을 JSON으로 변환하기
				String hi = "안녕하세요.";
				jsonData = gson.toJson( hi );
				break;
			case "arr":
				int [] arr = {100, 200, 300, 400, 500};
				jsonData = gson.toJson( arr );
				break;
			case "obj":
				SampleVO vo = new SampleVO(100, "홍길동");
				
				jsonData = gson.toJson(vo);
				break;
			case "list":	// 자바의 List객체를 JSON으로 변환하기
				List<SampleVO> list = new ArrayList<>();
				list.add( new SampleVO(1, "이순신") );
				list.add( new SampleVO(2, "강감찬") );
				list.add( new SampleVO(3, "변학도") );
				list.add( new SampleVO(4, "성춘향") );
				
				jsonData = gson.toJson(list);
				break;
			case "map":
				Map<String, String> map = new HashMap<>();
				map.put("name", "이몽룡");
				map.put("tel", "010-1234-5678");
				map.put("addr", "대전시 중구 대흥동");
				jsonData = gson.toJson(map);
				break;
		}
		
		System.out.println(choice +"  :  " + jsonData);
		
		out.println(jsonData);
		// response 객체의 buffer를 flush해라 ( 비워라 )
		response.flushBuffer();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
