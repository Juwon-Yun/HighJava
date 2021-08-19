package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
 	ResourceBundle객체 => 파일의 확장자가 Properties인 파일의 내용을 읽어와 
 									 key값과 value값을 분리해서 정보를 갖는다
 								=> 읽어올 파일의 내용은 'key값=value값' 형태로 되어 있어야 한다.
 								
 */
public class ResourceBundleTest {
	public static void main(String[] args) {
		// ResourceBundle 객체를 이용하여 파일 내용 읽어오기
		
		// ResourceBundle 객체 생성하기
		// getBundle( ) 메소드에 파일을 지정할 때 패키징 표기법으로 지정한다.
		// 그리고, 마지막 파일명의 확장자는 지정하지 않는다.
		//				(이유 : 확장자는 항상 '.Properties'이기 때문에 따로 확장자를 입력하지않는다)
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		// 읽어온 내용 출력하기
		System.out.println("driver : " + bundle.getString("driver"));
		System.out.println("url : " + bundle.getString("url"));
		System.out.println("user : " + bundle.getString("user"));
		System.out.println("pass : " + bundle.getString("pass"));
	}
}
