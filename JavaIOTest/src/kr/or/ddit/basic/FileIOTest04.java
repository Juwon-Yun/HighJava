package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
			//사용자가 입력한 내용을 그대로 파일로 저장하기
			//문자 기반 스트림 이용
			
			// System.in, System.out => 콘솔(표준 입출력장치)
			// System.in(입력용 => 키보드), System.out(출력용 => 화면)

		    //바이트 기반 스트림으로 변환해주는 보조 스트림
			// 						-> InputStreamReader (입력용)
			//						-> OutputStreamWriter (출력용)
		    InputStreamReader isr = new InputStreamReader(System.in);
															// System.in : 기반 스트림 역할을 해줌
			FileWriter fw = null;
			try {
				fw = new FileWriter("d:/d_other/testChar.txt");
				int c;
				System.out.println("아무거나 입력하세요.(입력의 끝은 Ctrl + Z 입니다)");
				while( (c = isr.read()) != -1) {
					fw.write(c);
				}
				System.out.println("작업 끝!!!");
				
				isr.close();
				fw.close();
				
			} catch (IOException e) {}
			
			
	}
}
