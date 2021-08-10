package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("접속을 기다립니다!!!");
		
		Socket socket = server.accept();
		System.out.println("클라이언트와 접속되었습니다!!!");
		System.out.println();
		
		String copyadd = "e:/d_other/down/스폰지밥.png";
		
		InputStream fis = socket.getInputStream();
		DataInputStream dins = new DataInputStream(fis);
		
		
		File file = new File(copyadd);
		FileOutputStream fout = new FileOutputStream(file);
		DataOutputStream dout = new DataOutputStream(fout);
		
		int data;
		while( (data = dins.read()) != -1) {
			dout.write(data);
		}
		
		dins.close();
		dout.close();
	}
}
