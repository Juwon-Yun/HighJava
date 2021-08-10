package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소켓을 통해서 메세지를 보내는 역할을 담당하는 쓰레드 클래스 이다.
public class Sender extends Thread{
	private Socket socket;
	private DataOutputStream dos;
	private String name;
	private Scanner scan;
	
	public Sender(Socket socket){
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.println("이름 입력: ");
		name = scan.nextLine();
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dos!=null) {
			try {
				//Scanner : 엔터치기 전까지 block 
				//메세지를 입력받아 '이름' 뒤에 붙여서 전송한다.
				dos.writeUTF(name + " : " + scan.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
