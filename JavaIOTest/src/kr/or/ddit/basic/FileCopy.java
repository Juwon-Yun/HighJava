package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 		D 드라이브에 d_other폴더에 있는 '스폰지밥.png' 파일을
 		D 드라이브의 d_other폴더의 하위 폴더 중에서 '연습용'폴더에 
 		'스폰지밥_복사본.png' 파일로 복사하는 프로그램을 작성하시오.

 */
public class FileCopy {

	public static void main(String[] args) {
		new FileCopy().fileCopyStart();
//		text파일 복사하기
//		// 1.원본 File, 복사할File 준비
//		File file = new File("d:/d_other/test.txt");
//		File newFile = new File("d:/d_other/연습용/test.txt");
//		
//		try {
//			FileInputStream input = new FileInputStream(file);
//			FileOutputStream output = new FileOutputStream(newFile);
//		
//		byte[] buf = new byte[1024];
//		
//		int c;
//		while( (c = input.read(buf)) > 0) {
//			output.write(buf,0,c);
//			System.out.println("파일 복사 성공!!!");
//		}
//
//		input.close();
//		output.close();
//		} catch (IOException e) {
//			System.out.println("알 수 없는 입출력입니다.");
//		}
	}
	
	
		public void fileCopyStart() {
//			File file = new File("d:/d_other/스폰지밥.jpg");
			File file = openDialog();
			File targetfile = saveDialog();
			
			if(file==null || !file.exists()) {
				System.out.println("원본 파일이 없습니다");
				System.out.println("복사 작업을 중지합니다");
				return;
			}
			
			if(targetfile==null) {
				System.out.println("대상 파일을 선택하지 않았습니다.");
				System.out.println("복사 작업을 중지합니다.");
				return;
			}
			
			FileInputStream fin = null;
			FileOutputStream fout = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				//바이트기반 입력스트림
				fin = new FileInputStream(file);	
				bis = new BufferedInputStream(fin);
//				fout =new FileOutputStream("d:/d_other/연습용/스폰지밥_복사본.jpg");
				fout =new FileOutputStream(targetfile);
				bos = new BufferedOutputStream(fout);
				
				System.out.println("복사 시작!!!");
				int data;
				
//				while( (data = fin.read()) != -1 ) {
//					fout.write(data);
//				}
				
				while( (data = bis.read()) != -1 ) {
					bos.write(data);
				}
				bos.flush();
				System.out.println("복사 완료!!!");
			}catch (IOException e) {	
				
			}finally {
				if(fin != null) {
					try {
						fin.close();
					}catch(IOException e) {}
				}//if문
				if(fout !=null) {
					try {
					fout.close();
					}catch (IOException e) {	}
				}//if문
				if(bis != null) {
					try {
						bis.close();
					}catch (IOException e) {}
				}//if문
				if(bos != null) {
					try {
						bos.close();
					} catch (IOException e) {}
				}//if문
			}//finally문
			
			
		}
		//---------------------------------------------------
		
		//열기용 창을 보여주고 선택한 파일을 변환하는 메소드
		private File openDialog() {
			// SWING의 파일 열기, 저장 창 연습
			JFileChooser chooser = new JFileChooser();
			
			//보여줄 파일의 확장자 설정
			//여러가지 일때는 String 배열을 쓰기도하고 나열하기도 한다
			FileNameExtensionFilter img  =
					new FileNameExtensionFilter("Image File", new String [] {"png", "jpg", "jpeg", "gif"});
			FileNameExtensionFilter txt = 
					new FileNameExtensionFilter("Text File", "txt");
			FileNameExtensionFilter doc = 
					new FileNameExtensionFilter("Ms-word", "docx", "doc");
		
			chooser.addChoosableFileFilter(img);
			chooser.addChoosableFileFilter(txt);
			chooser.addChoosableFileFilter(doc);
			
			//확장자 목록 중에 기본적으로 선택될 확장자 지정 
			chooser.setFileFilter(txt);
			
			// 전체 파일 목록 (*.*) 표시 여주 설정 (true: 설정, false: 해제) (파일 유형에서 모든파일)
			chooser.setAcceptAllFileFilterUsed(true);
			
			//Dialog창에 나타날 기본 경로 설정
			chooser.setCurrentDirectory(new File("d:/d_other"));
			
			//열기용 창
			int result = chooser.showOpenDialog(new Panel());
			
			//저장용 창
//			int result = chooser.showSaveDialog(new Panel());
			
			// '저장' 또는 '열기' 버튼을 눌렀을 경우의 처리하기
			File selectedFile = null;
			if( result == JFileChooser.APPROVE_OPTION ) {
					//선택한 파일 객체 구하기
				selectedFile = chooser.getSelectedFile();
				System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
				
				//선택한 파일 정보를 이용해서 실제 읽기 또는 쓰기 작업을 수행한다.
			}
			return selectedFile;
	}
	//----------------------------------------------------------------
		
		private File saveDialog() {
			// SWING의 파일 열기, 저장 창 연습
			JFileChooser chooser = new JFileChooser();
			
			//보여줄 파일의 확장자 설정
			//여러가지 일때는 String 배열을 쓰기도하고 나열하기도 한다
			FileNameExtensionFilter img  =
					new FileNameExtensionFilter("Image File", new String [] {"png", "jpg", "jpeg", "gif"});
			FileNameExtensionFilter txt = 
					new FileNameExtensionFilter("Text File", "txt");
			FileNameExtensionFilter doc = 
					new FileNameExtensionFilter("Ms-word", "docx", "doc");
		
			chooser.addChoosableFileFilter(img);
			chooser.addChoosableFileFilter(txt);
			chooser.addChoosableFileFilter(doc);
			
			//확장자 목록 중에 기본적으로 선택될 확장자 지정 
			chooser.setFileFilter(txt);
			
			// 전체 파일 목록 (*.*) 표시 여주 설정 (true: 설정, false: 해제) (파일 유형에서 모든파일)
			chooser.setAcceptAllFileFilterUsed(true);
			
			//Dialog창에 나타날 기본 경로 설정
			chooser.setCurrentDirectory(new File("d:/d_other"));
			
			//열기용 창
//			int result = chooser.showOpenDialog(new Panel());
			
			//저장용 창
			int result = chooser.showSaveDialog(new Panel());
			
			// '저장' 또는 '열기' 버튼을 눌렀을 경우의 처리하기
			File selectedFile = null;
			if( result == JFileChooser.APPROVE_OPTION ) {
					//선택한 파일 객체 구하기
				selectedFile = chooser.getSelectedFile();
				System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
				
				//선택한 파일 정보를 이용해서 실제 읽기 또는 쓰기 작업을 수행한다.
			}
			return selectedFile;
	}
	//==========================================
		
		
}//class
