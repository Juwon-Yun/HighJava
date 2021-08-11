package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {
	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	public void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp,7777);
			System.out.println("서버에 연결되었습니다");
			
			//메세지 전송용 쓰레드 생성
			ClientSender sender = new ClientSender(socket);
			
			//메세지 수신용 쓰레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// clientStrart() 메소드 끝
	
}

class ClientSender extends Thread{
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private String name;
	private Scanner sc;
	
	public ClientSender(Socket socket) {
		this.socket = socket;
		sc = new Scanner(System.in);
		
		try {
			//수신용
			dis = new DataInputStream(socket.getInputStream());
			//송신용
			dos = new DataOutputStream(socket.getOutputStream());
			
			if(dos != null) {
				// 처음 클라이언트 프로그램이 시작하면 대화명(이름)을 서버로 전송하고
				// 대화명의 중복여부를 feedback으로 받아서 확인한다.
				System.out.print("대화명 : ");
				String name = sc.nextLine();
				
				while(true) {
					//대화명을 전송한다.
					dos.writeUTF(name);
				
					//대화명의 중복 여부를 받는다.
					String feedback = dis.readUTF();
					//대화명이 중복될때
					if(feedback.equals("이름중복")) {
						System.out.println(name + "은 이름이 중복됩니다.");
						System.out.println("다른 대화명으로 다시 입력하세요.");
						System.out.print("대화명 : ");
						name = sc.nextLine();
					}else {
						this.name = name;
						System.out.println("[" + name + "] 이름으로 대화방에 입장했습니다.");
						break;
					}
				}//while문
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// 생성자
	
	@Override
	public void run() {
		try {
			while(dos!=null) {
				//키보드로 입력한 메세지를 서버로 전송한다
				dos.writeUTF("[" + name + "] " + sc.nextLine());
			}
		} catch (IOException e) {
		}
	}
}

//메세지 수신용 쓰레드 
class ClientReceiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	
	public ClientReceiver(Socket socket){
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
		}
	}//생성자 끝
	
	@Override
	public void run() {
		try {
			while(dis != null) {
				//서버로부터 받은 메세지를 화면에 출력한다.
				System.out.println(dis.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}





