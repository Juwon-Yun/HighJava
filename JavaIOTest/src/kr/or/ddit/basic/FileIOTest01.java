package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반의 스트림을 이용하여 파일 내용을 읽어오기
		//		=> FileInputStream을 이용한다.
		
		// try-catch 블럭으로 묶어준다
		try {
			// 방법1) 읽어올 파일 경로 정보를 문자열로 지정하기
			// 입력용 파일 스트림 객체 생성
			// FileInputStream fin = 
			// 		new FileInputStream("d:/d_other/test.txt");

			// 방법2) 읽어올 파일 정보가 저장될 File 객체를 이용하는 방법
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			//읽어올 데이터를 저장할 변수
			int c;
			while( ( c = fin.read() ) != -1  ) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print( (char) c + " ");
			}
			
			//객체 닫아주기
			fin.close();
		} catch(FileNotFoundException e) {
			System.out.println("지정한 파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("알 수 없는 입출력 오류입니다.");
		}
	
	
	}
}
