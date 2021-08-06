package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력할 보조스트림 DataInputStream 객체 생성
			DataOutputStream dos = new DataOutputStream(fout); 
			
			// 정수형으로 출력
			dos.writeInt(200);
			// 실수형(float)으로 출력
			dos.writeFloat(1234.5f);
			// 논리형으로 출력
			dos.writeBoolean(true);
			// 문자열으로 출력
			dos.writeUTF("ABCD안녕abcd");
			
			System.out.println("출력 완료!!!");
			dos.close();
			
			DataInputStream dis = new DataInputStream(new FileInputStream("d:/d_other/test.dat"));
			
			// DataInputStream으로 자료를 읽어올 때는
			// 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + dis.readInt());
			System.out.println("실수형 : " + dis.readFloat());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("문자열 : " + dis.readUTF());
			
			System.out.println("읽기 작업 끝!!!");
			
			//스트림 닫기
			dis.close();
		} catch (IOException e) {}

	}
}
