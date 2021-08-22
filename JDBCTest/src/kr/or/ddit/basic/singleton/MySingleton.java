package kr.or.ddit.basic.singleton;

/*
 	Singleton 패턴 => 객체가 1개만 만들어지게 하는 방법
			(외부에서 new 명령을 사용하지 못하게 한다.)
		- 사용이유
			1) 메모리 낭비 방지
			2) 데이터의 공유가 쉽다
		
		- singleton패턴 클래스를 만드는 방법(필수 구성 요소)
		1. 자신 class의 참조값이 저장될 변수를 private static으로 선언한다
		2. 생성자의 접근 제한자를 private으로 한다(외부에서 객체생성 제한)
		3. 자신 class의 인스턴스를 생성하고, 해당 인스턴스를 반환하는 메소드를
			public static으로 작성한다.
			(이 메소드의 이름은 보통 getInstance로 한다.)
			
 */
public class MySingleton {
	// 1번
	private static MySingleton single;
	
	//2번
	private MySingleton() {
		System.out.println("생성자 입니다.");
	}
	
	//3번
	public static MySingleton getInstance() {
		if ( single == null ) single = new MySingleton();
		return single;
	}
	
	// 기타 이 클래스가 처리할 내용들을 기술한다.
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메소드 호출입니다");
	}
}


















