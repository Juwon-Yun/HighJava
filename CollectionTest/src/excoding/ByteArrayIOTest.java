package excoding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	// 자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			int len; // 실제 읽어온 byte수를 반환한다.
			while((len = bais.read(temp)) != -1) {

				// temp 배열의 내용 중에서 0번째 부터 len 개수만큼 출력
				baos.write(temp, 0, len);
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			outSrc = baos.toByteArray();
			
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			//스트림 객체 닫아주기
			bais.close();
			baos.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}