package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		//TCP소켓 통신을 하기 위해 ServerSocket 객체를 생성한다.
		ServerSocket server = new ServerSocket(7777);
		
		//보내는건 outputstream, write
		// 받는건 inputStream, read 이다.
		
		
		System.out.println("접속을 기다립니다!!!");
		
		// accept( ) 메소드 => 클라이언트의 연결 요청이 올 때까지 계속 기다린다.
		// 							  연결 요청이 오면 새로운 Socket 객체를 생성해서
		//							  요청한 클라이언트의 Socket객체와 연결한다.
		Socket socket = server.accept();
		
		// accept( ) 메소드 이후의 소스는 연결이 완료되어야만 실행된다.
		System.out.println("클라이언트와 연결되었습니다!!!");
		System.out.println();
		
		//상대방
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		//자신
		System.out.println("연결된 서버(내 컴퓨터) 정보");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " +socket.getLocalPort());
		System.out.println();
		
		// 클라이언트에게 메세지 보내기
		// 					=> Socket의 OutputStream 객체를 이용해서 전송한다.
		// 						 Socket객체변수.getOutputStream( ) 메소드 이용
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		//클라이언트로 메세지 보내기
		dos.writeUTF("환영합니다. 어서오세요.");
		System.out.println("메세지를 보냈습니다!!!");
		
		//소켓과 연결 스트림 닫기
		dos.close();
		socket.close();
		server.close();
		
		// cmd 창 두개 띄워서 입력하면 Client, Server로 나눠서 실행할 수 있다.
		// E:\Java_workspace\3.HighJava\workspace\javaNetwork\bin>java kr.or.ddit.basic.tcp.TcpServer01 
		// E:\Java_workspace\3.HighJava\workspace\javaNetwork\bin>java kr.or.ddit.basic.tcp.TcpClient01
	}

}
