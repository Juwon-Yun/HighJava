package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte [] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte [] outSrc = null;
		
		//스트림 선언 및 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		// 입력용 스트림으로 데이터를 읽어와 출력용 스트림으로 출력하기
		// 한번에 1byte식 읽어온다.
		// 리터럴로 가져오고 반환값은 int형이다.
		
		//읽어온 데이터가 저장될 변수 선언
		int data;
		// read( )메소드는 더이상 읽을 자료가 없을 때까지 무한루프
		while( (data = input.read())  != -1){
			//읽어온 데이터 출력하기
			output.write(data);
		}
		
		//출력된 스트림값들을 byte 배열로 변환해서 반환하는 메소드
		outSrc = output.toByteArray();
		
		try {
			//입출력 작업을 모두 마치면 사용했던 스트림을 닫아준다
			// => 자원 반납 (try catch 블럭 필수)
			input.close();
			output.close();
		} catch (IOException e) {}
		
		System.out.println("  inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
	}

}
