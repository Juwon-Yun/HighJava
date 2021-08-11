package kr.or.ddit.basic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP??
// 비연결지향적
// 데이터의 신뢰성을 보장하지 않는다
// TCP에 비해 전송 속도가 빠르다(게임프로그래밍, 동영상스트리밍)

// UDP방식 : 비연결 지향, 신뢰성이 없다
// 				  데이터가 순서대로 도착한다는 보장이 없다.
//				  그렇지만 TCP방식보다 속도가 빠르다.

//	DatagramSocket객체와 DatagramPacket객체를 이용해서 통신한다.
//		- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
//		- DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다.(소포, 편지)
//   				=> 수신용 생성자와 송신용 생성자를 따로 제공한다.

//	TCP의 경우에는 스트림을 이용하여 송수신하지만
// UDP의 경우에는 데이터그램을 이용하여 송수신한다.

public class UdpServer {
	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다. (1024번이상을 주로사용)
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inPacket, outPacket;
			System.out.println("서버 실행 중");
			
			// 에코: 클라이언트가 어떤 메세지를 서버에 보내면 받은 데이터를 그대로 클라이언트에게 전달해서 송수신이 잘되는지 확인한다
			while(true) {
				// 데이터(패킷 수신)가 저장될 byte배열 선언
				byte [] bMsg = new byte[512];
				
				//  수신용 패킷 객체를 생성
				// 		=> 수신된 데이터가 저장될 byte형 배열, 배열의 길이를 지정해서 생성한다. 
				inPacket = new DatagramPacket(bMsg, bMsg.length);
				
				
				// 데이터를 수신한다.  => receive( ) 메소드 이용
				//				=> 이 메소드는 데이터가 올 때까지 기다린다.
				//				=> 수신된 데이터의 패킷정보는 인수로 지정한 패킷변수에 저장된다.
				
				// 소켓을 만들어서 미리 대기하고 있어야한다.
				// 없으면 날아온 패킷이 증발함 
				// 상대방이 데이터 송신하기 전까지 block
				socket.receive(inPacket);
				
				System.out.println("패킷 수신 완료");
				//수신 받은 패킷객체를 이용하여 상대방의 주소, 포트번호를 알 수 있다.
				// 상대방 아이피 주소
				InetAddress address = inPacket.getAddress();
				//상대방 포트번호
				int port = inPacket.getPort();
				
				// 상대방에게 데이터를 송신하려면 ip, port번호를 알아야 할 수 있다.
				System.out.println("상대방 IP정보 : " + address);
				System.out.println("상대방 Port번호 : " + port);
				System.out.println();
				
				// 상대방이 보낸 메시지 출력하기
				// 수신용 패킷에서 수신된 데이터와 관련된 정보를 알려주는 메소드
				//  -inpacket.getLength( ) -> 실제 읽어온 데이터의 길이를 반환한다.
				//  -inpacket.getData( ) -> 실제 읽어온 데이터를 byte형 배열로 반환한다.
				//				=> byte 배열 반환이 내장되어있음
				
				// 남은 byte 데이터가 없이, 데이터 손실없이 0부터 inPacket.length의 길이 만큼 문자열에 넣는다
				String msg = new String(bMsg, 0, inPacket.getLength(), "UTF-8");
				// 다른방법(같은 값이다)
//				String msg2 = new String(inPacket.getData(), 0, inPacket.getLength());
				
				// 통신 종료 메세지 확인
				if("/end".equals(msg)) {
					break;
				}
				
				System.out.println("클라이언트가 보낸 메세지 : " + msg);
				System.out.println();
				
				//----------------------------------------------------------------------
				
				// 상대방에게 메세지 보내기(수신 받은 메세지를 그대로 송신한다.)
				
				// 송신할 데이터(메세지)를 byte형 배열로 변환한다
				byte [] sendMsg = msg.getBytes("utf-8");
				
				// 송신용 패킷객체 생성하기
				// 		=> 전송할 데이터가 저장된 byte형 배열
				//		=> 전송할 자료의 길이(배열의 길이), 상대방 주소 정보, 포트 번호
				//		=> 위 4가지를 지정하여 생성한다.
				outPacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기 => send( )메소드 이용
				socket.send(outPacket);
				System.out.println("송신 완료");
				
			}//while문 끝
			
			System.out.println("통신 끝");
			socket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}//main 
}
