package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		/*
		    한글이 저장된 파일 읽어오기
		    			=> 인코딩 방식을 지정해서 읽어오기
		    			=> InputStreamReader를 이용해서 작업한다.
		    			
		 */
		try {
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
//			
//			int c;
//			
//			while( (c=fr.read()) != -1) {
//				System.out.print( (char) c );
//			}
//			
//			fr.close();
//			
			FileInputStream fis =
			//  	new FileInputStream("d:/d_other/test_utf8.txt");
					new FileInputStream("d:/d_other/test_ansi.txt");
			
			//기본 인코딩 방식으로 읽어온다
			// InputStreamReader isr = new InputStreamReader(fis);
			
			// 인코딩 방식을 지정해서 사용하기
			// 인코딩 방식 예시
			//   - MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			//   - UTF-8  => 유니코드 UTF-8 인코딩 방식
			//   - US-ASCII => 영문 전용 인코딩 방식
			
			InputStreamReader isr = new InputStreamReader(fis, "MS949");

			int c; 
			while ( (c=isr.read()) != -1) {
				System.out.print((char) c);
			}
			
			//보조 스트림을 닫으면 같이 사용된 기반이되는 스트림도 자동으로 닫힌다.
			isr.close();
		
		} catch (IOException e) {}
		
		System.out.println();
		System.out.println();

		try {
			FileReader fr = 
//					new FileReader("d:/d_other/test_utf8.txt");
					new FileReader("d:/d_other/test_ansi.txt");
			
			int c;
			
			while( (c=fr.read()) != -1) {
				System.out.print( (char) c );
			}
			
			fr.close();
		} catch (IOException e) {}
	
	}
}
