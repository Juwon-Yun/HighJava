package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//소켓이 생성되면 전송할 파일의 파일명을 먼저 전송한 후 
// 파일의 내용을 읽어서 전송한다.


public class TcpFileServer_Teacher {

	public static void main(String[] args) {
		File saveDir = new File("e:/d_other/down");
		
		if( !saveDir.exists() ) {
			saveDir.mkdirs();
		}
		
		ServerSocket server = null;
		Socket socket = null;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		DataInputStream dis = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다");
			
			socket = server.accept();
			
			System.out.println("파일 다운로드 시작");
			InputStream is = socket.getInputStream();
			dis = new DataInputStream(is);
			
			// 첫 번쨰로 수신받은 데이터는 전송되어 올 파일명
			String fileName = dis.readUTF();
			
			//저장할 파일위치와 파일명을 지정하여 File객체를 생성한다.
			File saveFile = new File(saveDir, fileName);
			
			// 소켓에서 파일 내용을 읽어와 파일로 출력한다.
			// 입력용 스트림(소켓)
			bis = new BufferedInputStream(is);
			// 출력용 스트림(파일)
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			//실제 작업
			byte [] temp = new byte[1024];
			int length = 0;
			
			while ( (length = bis.read(temp)) > 0) {
				bos.write(temp, 0 , length);
			}
			bos.flush();
			System.out.println("파일 다운로드 완료");
			
			
		} catch (IOException e) {
			System.out.println("파일 다운로드 실패");
			e.printStackTrace();
		}finally {
			// 사용했던 자원들을 반납한다.
			if( dis !=null ) try { dis.close(); } catch(IOException e) {}
			if( bos !=null ) try { bos.close(); } catch(IOException e) {}
			if( bis !=null ) try { bis.close(); } catch(IOException e) {}
			if( socket !=null ) try { socket.close(); } catch(IOException e) {}
			if( server !=null ) try { server.close(); } catch(IOException e) {}
		}
	}

}
