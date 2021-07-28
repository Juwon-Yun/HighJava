package phonBookTest_Teacher;

import java.util.Properties;


public class PropertiesTest {
	
	/*
	 	-Properties객체는 Map보다 축소된 기능의 객체라고 할 수 있다.
	 	-Map은 key값과 value값에 모든 형태의 객체를 사용할 수 있지만
	 	  Properties객체는 key와 value에 String만 사용할 수 있다.
		-Map은 put( ), get( ) 메서드로 데이터를 입축력 하지만
		  Properties는 setProperty( ), getProperty( ) 메서드를 통해서 입출력한다.
		-Propertyes는 데이터를 파일로 입출력할 수 있다.
	 */
	public static void main(String[] args) {
		
	Properties prop = new Properties();
	
	prop.setProperty("name", "홍길동");
	prop.setProperty("tel", "010-1234-1234");
	prop.setProperty("age1", "20");
	prop.setProperty("age2", String.valueOf(20));
	prop.setProperty("addr", "대전");
	
	String name = prop.getProperty("name");
	int age =Integer.parseInt(prop.getProperty("age2"));
	String tel = prop.getProperty("tel");
	String addr = prop.getProperty("addr");
	
	System.out.println("이름: "+ name);
	System.out.println("나이: "+age);
	System.out.println("전화: "+tel);
	System.out.println("주소: "+addr);
	
	}
}
