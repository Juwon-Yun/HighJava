package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// 이 클래스는 소켓에서 메세지를 받아서 화면에 출력하는 역할을 담당하는 쓰레드 클래스 이다.

public class Receiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	
	//생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			// 출력용 스트림 객체 생성
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try{
				//Sender에서 writeUTF 호출 전까지 block하고
				//writeUTF 호출 시작하면 그때부터 read 시작 
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
