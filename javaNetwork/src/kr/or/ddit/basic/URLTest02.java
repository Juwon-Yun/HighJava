package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLTest02 {
	public static void main(String[] args) throws IOException {
		
//		//URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상클래스
		
//		//특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		
		URL url = new URL("https://www.naver.com/index.html");
		
		//URL 객체를 이용해서 URLConnection 객체 구하기 
//		URLConnection urlcon = url.openConnection();
//		
		// 첫 번째 방법 
//		//Header정보 구하기
//		Map<String, List<String>> headerMap = urlcon.getHeaderFields();
//		
//		//HeaderMap의 key값과 value값 출력하기
//		// naver.com/index.html은 F12 > 네트워크 > index.html > 머리글 에서 볼 수 있다
//		for (String headerKey : headerMap.keySet()) {
//			System.out.println(headerKey + " : " + headerMap.get(headerKey));
//		}
//		System.out.println("-----------------------------------------------------");
//		
//		//해당 문서의 내용을 가져오기 (즉, index.html 문서 내용 가져오기)
//		
//		// 방법1 => URLConnection객체를 이용하는 방법
//		
//		// 파일을 읽어오기 위한 스트림 객체 생성
//		// 바이트 기반의 Stream
//		InputStream is = urlcon.getInputStream();
//		
//		// 바이트 기반의 Stream을 문자 기반 Stream으로 변환
//		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//		
//		// Buffered 보조스트릠 객체 생성
//		BufferedReader br = new BufferedReader(isr);
//		
//		//자료를 읽어와 출력하기
//		while(true) {
//			String str = br.readLine();
//			if(str == null) break;
//			System.out.println(str);
//		}
//		
//		//Stream 닫기
//		br.close();
		
		//===================================================
		//방법 2 => URL객체의 openStream() 이용하기
		InputStream is2 = url.openStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
		
		//자료를 읽어와 출력하기
		while(true) {
			String str = br2.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		
		//Stream 닫기
		br2.close();
	}
}
