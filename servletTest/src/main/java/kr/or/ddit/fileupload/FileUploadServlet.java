package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
 	- Servlet 3.0이상에서 파일을 업로드하려면 서블릿에 @MultipartConfig 어노테이션을 설정해야 한다.
 	- @MultipartConfig 어노테이션에서 설정할 변수들
 	   1) location : 업로드한 파일이 임시적으로 저장될 경로 지정 : ( 기본값 : "" )
 	   2) fileSizeThreshold : 이 속성에 설정한 값보다 큰 파일이 전송되면 (1024 * 1024 => 1메가)
 	   			location에 지정한 임시 디렉토리에 저장한다.
 	   			( 단위 : byte, 기본값 : 0 (무조건 임시디렉토리에 저장된다) )
 	   3) maxFileSize : 1개의 파일의 최대 크기 설정 ( 단위 : byte, 기본값 : -1L(무제한) )
 	   4) maxRequestSize : 서버로 전송되는 전체 Request데이터의 최대 크기 설정
 	   					   ( 단위 : byte, 기본값 : -1L(무제한) ) => (파일 + formData)
 		
 	
 */
@WebServlet("/fileUpload.do")
@MultipartConfig( fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024*1024*30, maxRequestSize = 1024*1024*100 )
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 파일 전송은 POST방식만 사용 가능하다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
		String uploadPath = "E:\\D_Other\\uploadFiles";
		
		// 저장될 폴더가 없으면 새로 생성한다.
		File fileuploadDir = new File(uploadPath);
		if ( !fileuploadDir.exists() ) {
			// 폴더를 만들어준다
			fileuploadDir.mkdirs();
		}
		
		// ----------------------------------------------------------------------------
		// 파일이 아닌 일반 Form Data들은 getParameter( )메소드나 
		// getParameterValues( )메소드를 이용해서 구한다.
		String memId = request.getParameter("memid");
		System.out.println("일반 Form Data : " + memId);
		
		// ----------------------------------------------------------------------------
		
		// 파일명이 저장될 변수 선언
		String fileName = "";
		// 업로드한 파일의 정보를 저장할 List 생성
		List<UploadFileVO> fileList = new ArrayList<>();
		
		/*
		 	
		 	- Servlet 3.0이상에서 파일 정보를 관리하는 객체는 Part객체이다.
			- 전체 Part객체 구하기 => request.getParts( );
		 	- 개별적인 Part객체 구하기 => request.getPart("part이름");
		 	
		 	- Part객체에서 제공하는 메소드들
		 		1) getInputStream() : Part의 내용을 읽는데 사용하는 InputStream객체를 반환한다.
		 		2) getSize() : 업로드된 파일의 크기를 byte단위로 반환한다.
		 		3) getHeader(String name) : Part로 부터 지정한 name을 갖는 헤더값을 반환한다.
		 		4) write(String fileName) : 임시 저장되어 있는 파일 데이터를 fileName에 지정한 경로로 저장한다.

		 	
 		 */
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);
			
			// 구해온 파일명이 공백("")이면 이것은 파일이 아닌 일반 Form data라는 의미이다
			if( !"".equals(fileName) ) {
				// 1개의 업로드 파일 정보를 저장할 VO객체 생성
				UploadFileVO fvo = new UploadFileVO();
				
				fvo.setFileName(fileName);
				// 파일 크기는 KB단위로 저장
				fvo.setFileSize( (long)Math.ceil( part.getSize()/1024.0) );
			
				try {
					// 파일 저장
					part.write( uploadPath + File.separator + fileName );
					fvo.setUploadStatus("Success");
				}catch(IOException e) {
					fvo.setUploadStatus("Fail");
					e.printStackTrace();
				}
				fileList.add(fvo);
			}// if문
		}// for문
		
		request.setAttribute("memId", memId);
		request.setAttribute("uploadFileList", fileList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/fileupload/fileuploadList.jsp");
		rd.forward(request, response);
		
	}
	
	// Part객체에서 '파일명'을 찾아서 반환하드 메소드
	/*
	 	Part 구조
	 		1) 파일이 아닐 때 (즉, 일반 Form Data일 경우)
	 		--------------------------------------------------------------- => 각각의 Part를 구분하는 구분선
	 		    Content-Disposition : form-data; name="memid" => 파라미터명
	 								   => 빈줄
	 			a001				   => 파라미터값
	 	
	 	2) 파일일 경우
	 		--------------------------------------------------------------- => 각각의 Part를 구분하는 구분선
	 		    Content-Disposition : form-data; name="fileUpload1"; filename = "text1.txt" => 파일 정보
	 			Content-type : text/plain => 파일의 종류
	 								   => 빈줄
	 			abcd1234가나다 => 파일 내용
	 			
	 */
	private String extractFileName(Part part) {
		String fileName ="";
		// 헤더의 키값이 "Content-Disposition"인 헤더의 value값을 구한다.
		String contentDisposition = part.getHeader("Content-Disposition");
		String [] items = contentDisposition.split(";");

		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
		for(String item : items) {
			if( item.trim().startsWith("filename") ) {
				fileName = item.substring( item.indexOf("=") +2, item.length() -1 );
			}//if문
		}//for문
		return fileName;
	}
	
}
