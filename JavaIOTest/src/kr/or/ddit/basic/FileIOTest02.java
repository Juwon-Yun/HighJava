package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 바이트 기반의 스트림을 이용하여 파일에 출력하기
		try {
			// 출력용 스트림
			FileOutputStream fos = new FileOutputStream("d:/d_other/out.txt");
			
			System.out.println("출력작업 시작!!!");
			
			for(char ch= 'A'; ch <= 'Z'; ch++) {
				// ch변수의 데이터를 파일로 출력한다
				fos.write(ch);
				// 6개씩 출력하고 줄바꿈해라
				if( (ch-64) % 6 == 0) {
					fos.write('\n');
				}
			}
			System.out.println("파일에 쓰기 작업완료!!!");
			fos.close();
			
			// ================================
			// 위에서 저장한 파일을 읽어와 화면에 출력하시오.
			FileInputStream fis = new FileInputStream("d:/d_other/out.txt");
			int c;
			while( (c = fis.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝!!!");
			
			fis.close();
		} catch (IOException e) {
		}
		
	}

}
