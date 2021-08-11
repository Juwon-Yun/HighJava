package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 이것이 자바다 p.1056

public class TcpMultiChatServer {
	//접속할 클라이언트 정보를 저장할 Map객체 변수 선언
	// 		- key 값 : 접속한 사람의 이름
	// 		- value 값 : 접속한 클라이언트와 연결된 Socket 객체
	private Map<String, Socket> clientMap;
	
	public TcpMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<>());
	}
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다 \n");
			
			while(true) {
				//다수의 클라이언트 접속을 기다린다
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속했습니다.");
				
				//메세지를 받아서  전체에게 전송하는 Thread 실행하기
				ServerReciver receiver = new ServerReciver(socket); 
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//서버 소켓 닫기 
			if(server != null) {
				try {server.close();}catch(IOException e) {} 
			}
		}
				
	}//serverStart 메소드 끝
	
	// clientMap에 저장된 전체 접속자에게 메세지를 전송하는매서드
	private void sendToAll(String msg) {
		// clientMap의 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// 클라이언트의 키값의 OutputStream을 가져와 data를 보낸다 
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				dout.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// sendToAll 메소드 끝
	
	//Inner Class형태로 서버에서 클라이언트에 메세지를 전송하는 Thread를 만든다.
	// 		=> 이 쓰레드에서는 접속한 사람의 이름 중복 여부도 검사한다.
	class ServerReciver extends Thread {
		private Socket socket;
		//보내줄 데이터를 받아야함
		private DataInputStream dis;
		//보내야함
		private DataOutputStream dos;
		
		public ServerReciver(Socket socket) {
			this.socket = socket;
			try {
				// 소켓이 갖고있는 in, out Stream 데이터로 송신용 수신용을 만듬 
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// 생성자 끝
		
		@Override
		public void run() {
			String name = "";
			try {
				// 중복되지않을때까지 이름을 다시받아야하니 무한반복문 사용
				while(true) {
					//클라이언트가 최초로 보낸 데이터는 사용자의 이름인데
					//이 이름이 중복되었는지 여부를 feedback으로 클라이언트에게 보내준다.
					name = dis.readUTF();
					//이름이 중복되면 
					if(clientMap.containsKey(name)) {
						// 이름 중복 메세지 전송
						dos.writeUTF("이름중복");
					}else {
						dos.writeUTF("OK");
						break;
					}
				}//while문
				
				//while문을 탈출한 name은 중복이 아닌것
				//대화명(이름)을 받아서 전체 클라이언트에게 대화방 참여 메세지를 전송
				sendToAll("[" + name + "] 님이 들어오셨습니다.");
				
				// 대화명과 클라이언트와 연결된 Socket객체를 Map에 저장한다.
				clientMap.put(name, socket);
				
				//현재 접속자 수 출력
				System.out.println("현재 서버 접속자 인원수는 " + clientMap.size() + " 명 입니다.");
				
				// 한 클라이언트가 보낸 메세지를 다른 모든 클라이언트에게 보내준다
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				// 이 finally절이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.
				sendToAll("[" + name + "] 님이 채팅방을 나가셨습니다.");
				
				//Map에서 해당 대화명(이름)을 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + " 에서 접속을 종료하였습니다.");
				// 현재 접속자 수 출력
				System.out.println("현재 접속자 수는 " + clientMap.size() + " 명입니다.");
			}
			
		}//run 메소드 끝
		
	}//ServerReciver 클래스 끝
	
	
}
