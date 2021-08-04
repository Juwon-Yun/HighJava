package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest01 {

	public static void main(String[] args) {
		//File 객체 연습
		//1. new File(String 파일 또는 경로);
		// 		 => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일 사이의
		//			   구분 문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
		
		// '\'는 두번써줘야하고 '/'는 한번만 쓴다.
//		File file1 = new File("d:\\d_other\\test.txt");
		File file1 = new File("d:/d_other/test.txt");
		//실제 디스크에 test.txt가 생성되는 것은 아니지만
		// 이 파일을 다루기 위해 이 경로를 참조하는 파일 객체를 만듬
		
		System.out.println("파일이름 : " + file1.getName());
		System.out.println("디렉토리 일까? : " + file1.isDirectory());
		System.out.println("파일 존재 여부 : " + file1.isFile());
		System.out.println();
		
		File file2 = new File("d:/d_other/");
		System.out.println("파일이름 : " + file2.getName());
		System.out.println("디렉토리 일까? : " + file2.isDirectory());
		System.out.println("파일 존재 여부 : " + file2.isFile());
		System.out.println();
		 
		System.out.println(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println();
		// new File(File parent, String child)
		// 		=> 'parent' 디렉토리 안에 있는 'child'파일을 갖는다.
		
		File file3 = new File(file2, "test.txt");
		System.out.println("파일이름 : " + file3.getName());
		System.out.println("디렉토리 일까? : " + file3.isDirectory());
		System.out.println("파일 존재 여부 : " + file3.isFile());
		System.out.println();
		
		// 3. new File(String parent, String child)
		// 		=> 'parent' 디렉토리 안에 있는 'child'파일을 갖는다.
		File file4 = new File("d:/d_other", "test.txt" );
		System.out.println(file4.getAbsolutePath());
		System.out.println(file4.getPath());
		try {
			System.out.println(file4.getCanonicalPath());
		} catch (IOException e) {
		}
	 
		System.out.println("=======================");
		
		//디렉토리(폴더) 만들기
		/*
		 	-mkdir( ) => File객체의 경로중에서 제일 마지막 위치에 디렉토리를 만든다
		 						반환값
		 						생성완료 : true, 생성실패 : false
		 				  => 지정한 경로 중 중간부분의 결로가 모두 만들어져 있어야
		 				  	    마지막 위치의 디렉토리를 만들 수 있다.
		 				  	    
		 	-mkdirs( ) => File객체의 경로중에서 제일 마지막 위치에 디렉토리를 만든다
		 					     반환값
		 						 생성완료 : true, 생성실패 : false
		 				   => 중간 부분의 경로가 없으면 중간 부분의 경로도 같이 만들어 준다.
		 */
		
		File file5 = new File("d:/d_other/연습용");
//		System.out.println("디렉토리 일까? : " + file5.isDirectory());
//		System.out.println("파일 존재 여부 : " + file5.isFile());
		
		// exist( ) 메소드는 디렉토리(폴더)든 파일이든 둘다 검색해준다 
		System.out.println(file5.getName() + "의 존재 여부 : " + file5.exists() );
		
		if(file5.exists()) {
			file5.mkdir();
		}
		
		if(file5.mkdir()) {
			System.out.println(file5.getName() + "만들기 성공~~~~");
		}else {
			System.out.println(file5.getName() + "만들기 실패!!!");
		}
		
		File file6 = new File(file2, "test/java/src");
		//d:/d_other/test/java/src 이 없으면 실행
		if(!file6.exists()) {
			if(file6.mkdirs()) {
				System.out.println(file6.getName() + "만들기 성공!!!");
			}else {
				System.out.println(file6.getName() + "만들기 실패ㅠㅠㅠ");
			}
		}else if(file6.exists()){
			//src 삭제
			//file6.delete();
		}
		System.out.println("=======================");
		
		File f7 = new File("d:/d_other");
		File [] files = f7.listFiles(); 
		
		for (File file : files) {
			System.out.println(file.getName() + " => ");
		}
	}
}

