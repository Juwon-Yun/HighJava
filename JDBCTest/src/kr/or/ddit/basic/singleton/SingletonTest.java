package kr.or.ddit.basic.singleton;

public class SingletonTest {
	
	public static void main(String[] args) {
		// 외부에서 new 명령으로 생성 불가
		// MySingleton test1 = new MySingleton();
		
		// 싱글톤 객체 생성
		//생성자 입니다 <- 가 한번 출력되는것은 객체생성이 1번밖에 안됬기 때문이다
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2의 참조값(주소값) : " + test2.toString());
		System.out.println("test3의 참조값(주소값) : " + test3);
		
		System.out.println();
		
		if( test2 == test3 ) {
			System.out.println("참조값이 같네요 : " + (test2 == test3) );
		}
		System.out.println();
		System.out.println(test3.equals(test2));
		System.out.println();
		test2.displayTest();
		test3.displayTest();
		
	}
}
