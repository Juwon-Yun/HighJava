package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		// 문자 기반의 Buffered스트림 예제
		try {
			// 이클립스에서 자바 프로그램이 실행되는 현재 위치는
			// 해당 프로젝트 폴다가 현재 위치가 된다.
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			//읽어온 데이터가 저장될 변수
			String temp = "";
			// 문자 기반의 버퍼 메소드 중 readLine()메소드는 1줄 단위로 읽어온다.
			for (int i = 1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s \n", i, temp);
			} 
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
