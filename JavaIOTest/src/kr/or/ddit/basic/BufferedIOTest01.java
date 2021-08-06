package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		
		
		try {
			 fout = new FileOutputStream("d:/d_other/bufferTest.txt");
		
			//버퍼 크기가 5인 버퍼스트림 객체 생성
			//			=> 버퍼의 크기를 지정하지 않으면 기본 크기로 8kb(8192byte) //1kb => 1024byte
			bout = new BufferedOutputStream(fout, 5);
			System.out.println("작업 시작!!!");
			
			// 숫자 자체를 문자로 지정하기 위해 ' 사용
			for (int i = '1'; i <= '9'; i++) {
				//버퍼에 5바이트 넣고, 꽉차면 write로 외부로 옮김
				//flush 이용해 강제 방출 (강제로 write)
				bout.write(i);
			}
			//작업 종료전 버퍼에 남아있는 데이터를 모두 강제로 출력
			//close시 자동으로 호출됨
			bout.flush();
			
			System.out.println("작업 끝!!!");
			
			//fout.close();
			//보조스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 
			//자동으로 닫힌다.
			bout.close();
		} catch (IOException e) {}

		
	}
}
