package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 메소드(static)로 구성된 class 만들기
public class DBUtil {
	
	private static DBUtil instance;
	public static DBUtil getInstance() {
		if(instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}
	
	// 정적(static) 초기화 블럭
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "COM";
		String password = "java";
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("DB 로그인 실패");
			e.printStackTrace();
			return null;
		}
	}
}
