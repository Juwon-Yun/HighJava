package excoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 파일 읽기 예제
 */
public class FileStreamTest {
	public static void main(String[] args) {
		// FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fis = null;
		
		try {
			// 방법1 (파일경로 정보를 문자열로 저장하기)
//			fis = new FileInputStream("파일경로");	// 객체 생성
			
			// 방법2 (파일정보를 File 객체를 이용하여 지정하기)
			File file = new File("파일경로");	// 객체 생성
			fis = new FileInputStream(file);
			
			int c; // 읽어온 데이터를 저장할 변수
			while((c=fis.read()) != -1) {	// -1이 아니면 뭔가를 읽었다.
				// 읽어온 자료 출력하기
				System.out.print((char) c);
			}
			
			fis.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("지정된 파일이 없습니다.");
		} catch(IOException e) {
			System.out.println("알 수 없는 입출력 오류입니다");
		}
	}
}
