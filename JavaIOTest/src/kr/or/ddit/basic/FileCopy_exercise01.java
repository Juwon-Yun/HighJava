package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy_exercise01 {

	public static void main(String[] args) {
		//원본 파일경로
		String ori =  "d:/jdk1.8/test.jpg";
		//복사될 파일경로
		String copy =  "d:/jdk1.8/lib/test.jpg";
		
		//파일객체생성
		File orifile = new File(ori);
		//복사파일객체생성
		File copyfile = new File(copy);
		
		try {
			//읽을파일 객체
			FileInputStream fis = new FileInputStream(orifile);
			//복사할파일 객체
			FileOutputStream fos = new FileOutputStream(copyfile);
			
			int c;
			//fis.read() == -1 이면 파일을 다 읽은것
			while( (c = fis.read()) != -1 ) {
				//복사하기
				fos.write(c);
				System.out.println("파일 복사 완료!!!");
			}
			
			//자원 반납
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("올바른 경로 혹은 존재하지 않는 파일입니다.");
		}catch(IOException e) {
			System.out.println("알 수 없는 입출력입니다.");
		}
		
	}

}