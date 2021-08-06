package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
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
//		int result = chooser.showOpenDialog(new Panel());
		
		//저장용 창
		int result = chooser.showSaveDialog(new Panel());
		
		// '저장' 또는 '열기' 버튼을 눌렀을 경우의 처리하기
		if( result == JFileChooser.APPROVE_OPTION ) {
				//선택한 파일 객체 구하기
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
			
			//선택한 파일 정보를 이용해서 실제 읽기 또는 쓰기 작업을 수행한다.
			
			
		} 
		
		
		
		
		
		
		
		
		
	}

}
