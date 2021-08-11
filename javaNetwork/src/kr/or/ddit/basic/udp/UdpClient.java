package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//송신용, 수신용 패킷객체 변수 선언
		DatagramPacket inPacket, outPacket;
		
		// 수신 받은 데이터가 저장될 byte형 배열 선언
		byte [] bMsg = new byte[512];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			// 접속할 곳의 주소 정보 생성
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				//전송할 메세지를 입력한다.
				System.out.print("보낼 메세지 입력(/end 입력시 종료) : ");
				String msg = sc.nextLine();
				
				// 전송용 패킷 객체 생성
				byte [] sendMsg = msg.getBytes("utf-8");
				outPacket = new DatagramPacket(sendMsg, sendMsg.length, address, 8888);
				
				// 전송하기
				socket.send(outPacket);
				
				if("/end".equals(msg)) {
					break;
				}
				//----------------------------------------------------------------------
				// 서버에서 온 데이터를 받아서 출력하기
				
				// 수신용 패킷객체 생성
				inPacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 수신하기
				socket.receive(inPacket);
				
				// bMsg 바이트 배열의 데이터를 0부터 길이만큼 String data에 넣어준다 
				String data = new String(bMsg, 0, inPacket.getLength(), "UTF-8");
				System.out.println("서버의 응답 데이터 : " + data);
				System.out.println();
			}//while문 끝
			
			System.out.println("통신 끝");
			socket.close();
			sc.close();
		} catch (IOException e) {
		}
	}
}
