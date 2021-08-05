package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 		D 드라이브에 d_other폴더에 있는 '스폰지밥.png' 파일을
 		D 드라이브의 d_other폴더의 하위 폴더 중에서 '연습용'폴더에 
 		'스폰지밥_복사본.png' 파일로 복사하는 프로그램을 작성하시오.

 */
public class FileCopy {

	public static void main(String[] args) {
//		text파일 복사하기
//		// 1.원본 File, 복사할File 준비
//		File file = new File("d:/d_other/test.txt");
//		File newFile = new File("d:/d_other/연습용/test.txt");
//		
//		try {
//			FileInputStream input = new FileInputStream(file);
//			FileOutputStream output = new FileOutputStream(newFile);
//		
//		byte[] buf = new byte[1024];
//		
//		int c;
//		while( (c = input.read(buf)) > 0) {
//			output.write(buf,0,c);
//			System.out.println("파일 복사 성공!!!");
//		}
//
//		input.close();
//		output.close();
//		} catch (IOException e) {
//			System.out.println("알 수 없는 입출력입니다.");
//		}
		
		File jpeg = new File("d:/d_other/스폰지밥.png");
		byte [] bytes = new byte[(int)jpeg.length()];
		try {
			DataInputStream in1 = new DataInputStream(new FileInputStream(jpeg));
			in1.readFully(bytes);
			in1.close();
		} catch (IOException e) {}
		
		
		}
	}
