package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


// 파일을 읽어다가 socket에다 보내고 
// socket으로 받아서 파일을 읽는다 
public class TcpFileClient {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket("localhost", 7777);
		System.out.println("서버에 연결되었습니다!!!");
		
		System.out.println("클라이언트의 소켓 : " + socket);
		
		String copylink = "e:/d_other/스폰지밥.png";
		

		File orifile = new File(copylink);
		
		FileInputStream fin = new FileInputStream(orifile);
		DataInputStream dis = new DataInputStream(fin);
		
		
		OutputStream fout =  socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(fout);

		int data;
		while( (data = dis.read()) != -1 ) {
			dout.write(data);
		}
		
		System.out.println("연결을 종료합니다!!!");
		dis.close();
		dout.close();
		}
	}

