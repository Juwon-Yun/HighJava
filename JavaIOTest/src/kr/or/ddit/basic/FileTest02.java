package kr.or.ddit.basic;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest02 {

	public static void main(String[] args) {
		// 자바 프로그램의 실행 위치 구하기
		/* 방법1) => System.getProperty("user.dir") 명령 이용하기
															user.dir은 환경변수임
		*/
		
		String path1 = System.getProperty("user.dir");
		System.out.println("실행 위치 : " + path1);
		System.out.println();
		
		// 방법2) => 상대 경로에서 절대 경로로 변환하기
		Path relativePath = Paths.get("");
		String path2 = relativePath.toAbsolutePath().toString();
		System.out.println("실행 위치2 : " + path2);
		System.out.println();
		
		//방법3) => File객체 이용하기
		File file = new File("");
		String path3 = file.getAbsolutePath();
		System.out.println("실행 위치3 : " + path3);
		System.out.println();
		
		
		
		
	}

}
