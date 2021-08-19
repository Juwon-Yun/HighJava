package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 확장자가 properties인 파일의 내용을 읽어와 출력하는 예제
public class PropertiesTest {
	
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 지정한 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		try {
			// 입력 스트림 객체 생성
			fin = new FileInputStream(f);
			
			// 입력 스트림 객체를 이용하여 파일 내용을 읽어와 Properties객체에 저장하기
			
			// String만 쓸 수 있다
			// 주어진 입력 스트림을 이용하여 파일 내용을 읽어와 key값과 value값을
			// 분류한 후 Properties객체에 저장한다.
			prop.load(fin);
//			prop.load(new BufferedInputStream(fin));
			
			// 읽어온 정보를 출력해 보기
			System.out.println("driver : " + prop.getProperty("driver"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
		} finally {
			if( fin != null ) try { fin.close(); } catch (IOException e) {}
		}
		
	}
}
