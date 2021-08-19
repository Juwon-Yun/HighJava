package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 메소드(static)로 구성된 class 만들기
//(dbinfo.properties 파일을 이용하여 설정하기)

// 방법2) ResourceBundle 객체 이용하기
public class DBUtil3 {
	// ResourceBundle 객체 변수 선언
	static ResourceBundle bundle;
	
	// 정적(static) 초기화 블럭
	static {
		
		// 객체 생성
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "COM";
//		String password = "java";
		try {
//			return DriverManager.getConnection(url, user, password);
			return DriverManager.getConnection(bundle.getString("url"),
															  bundle.getString("user"),
															  bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB 로그인 실패");
			e.printStackTrace();
			return null;
		}
	}
}
