package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient02 {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7777);
			
			System.out.println("서버에 연결되었습니다.");
			System.out.println("클라이언트의 소켓 : " + socket);
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
