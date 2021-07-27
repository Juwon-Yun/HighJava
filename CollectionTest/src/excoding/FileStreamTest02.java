package excoding;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 */
public class FileStreamTest02 {
	public static void main(String[] args) {
		
		// 파일에 출력하기 
		FileOutputStream fos = null;
		
		try {
			// 출력을 OutputStream 객체 생성
			fos = new FileOutputStream("파일경로");
			
			for(char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			System.out.println("파일에 쓰기 작업 완료...");
			
			//쓰기 작업 완료 후 스트림 닫기
			fos.close();
			
			//==============================================
			//저장된 파일의 내용을 읽어 화면에 출력하기
			FileInputStream fis = new FileInputStream("파일경로");
			
			int c;
			while((c=fis.read()) != -1) {
				System.out.println((char) c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			fis.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}