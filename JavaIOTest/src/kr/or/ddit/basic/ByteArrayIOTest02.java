package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte [] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte [] outSrc = null;
		
		//입력용 스트림에 사용할 배열
		byte [] temp = new byte[4]; 
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			//읽어올 데이터가 있는지 확인
			int i= 0;
			while(input.available() > 0) {
				i++;
//				input.read(temp);
//				output.write(temp);
				
				//배열 길이 문제로 남는데이터 중복 해결방법
				//실제 읽어온 데이터 개수를 반환한다.
				int len = input.read(temp);
				//temp 배열의 내용 중 0번째부터 len의 개수만큼 출력한다.
				output.write(temp, 0, len);
				//	read할때 배열을 사용할때는 
				// 개수를 구하고 배열에서 실제 갯수만큼 꺼내온 다음 작업을 해야한다.
				System.out.println(i + "번째 읽어온 temp : " + Arrays.toString(temp));
			}
			
			//output에 저장된 데이터를 outSrc에 저장함
			outSrc = output.toByteArray();
			System.out.println();
			System.out.println("  inSrc => " + Arrays.toString(inSrc));
			/*
			 	1번째 읽어온 temp : [0, 1, 2, 3]
				2번째 읽어온 temp : [4, 5, 6, 7]
				3번째 읽어온 temp : [8, 9, 6, 7] 
				 	ㄴ8, 9를 읽어오고 데이터가 끝나니 길이만큼 남아있던 이전의 6,7을 그대로 가져온다
				
				  inSrc => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
				outSrc => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]

			 */
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			
			input.close();
			output.close();
		} catch (IOException e) {}

	}
}
