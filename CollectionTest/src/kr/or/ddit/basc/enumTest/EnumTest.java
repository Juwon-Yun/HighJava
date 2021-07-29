package kr.or.ddit.basc.enumTest;

import excoding.Enum_ex.Season;

/*
 - enum (열거형)  => 서로 관련있는 상수들의 집합을 나타낸다.
 							=> 클래스처럼 보이게하는 상수
 							=> 열거형은 클래스처럼 독립된 java파일에 만들수 있고,
 								  하나의 java 파일에 다른 클래스와 같이 만들수 있고,
 								  클래스 안에 내부 클래스처럼 만들 수 있다.
 	
 	- 열거형의 속성 및 메서드
 	 	1. name( ) => 열거형 상수의 이름을 문자열로 반환한다.
 	 	2.ordinal( ) => 열거형 상수가 정의된 순서(index 값)을 반환한다.
 	 						(0부터 시작)
 	 	3.valueOf("열거형상수명") => 지정된 열거형에서 '열거형상수명'과 일치하는 
 	 												  열거형 상수를 반환한다.
 	 	4.열거형 이름.상수명 =>  valueOf("상수명") 메소드와 같다.
 	 	
 -열거형 선언하기
 	방법1) 
 		enum 열거형이름 {상수값1, 상수값2, ..., 상수값n};
 	방법2)
 	 	enum 열거형이름 {
 	 		  상수명1(값들...), 상수명2(값들...), 상수명3(값들), ...., 상수명N(값들);
 	 }
 	 1번째가 2개면 2번째도 2개  3번째도 2개
 	 종류도 같아야함(정수형 문자형) 2개면 나머지도 (정수형 문자형) 이어야함 
	
	  //'값들'을 저장할 변수들 선언(갯수만큼)
	   private 자료형이름 변수명1;
	   ...
	  //열거형의 생성자를 만든다.
	  //열거형의 생성자는 '열거형상수'에 설정된 '값들'을 
	   								변수에 초기화하는 역할을 수행한다.
	  //열거형의 생성자는 묵시적으로 private 이다. (생략하면 자동으로 private)
	  
	  // '변수명'은 '값들'과 개수가 같고, 각각의 '값들'과 자료형이 맞아야 된다.
	  private 열거형이름(자료형이름 변수명, ...){
	  
	  }
	  
	  //구성된 '값들'을 외부에서 불러올 수 있도록 하기 위해서
	  // getter 메소드를 작성한다.
	   
	   
 */
public class EnumTest {
	//상수(Constant)로 사용할 값들을 미리 선언하여 사용할 때 사용하는 특별한 데이터 타입
	enum Test{
	}
	
	public enum Color {RED, GREEN, BLUE}
	public enum Count {ONE, TWO, THREE}
	
	public enum Season{
		봄("3월부터 5월까지"),		//상수명(값들) 형식의 선언
		여름("6월부터 8월까지"),
		가을("9월부터 11월까지"),
		겨울("12월부터 2월까지");
	
		private String str; 	//값이 저장될 변수 선언
		
		// 생성자 만들기: 제어자 - 묵시적으로 'private'
		// private : 접근제어, 클래스안에서만 사용가능,
		//				 사용시점에 자체적으로 생성자 호출해서
		// 				 필요한만큼만 생성함 (시즌은 4개, 시티는 5개)
		
		Season(String strMonth){	// => private Seaon(String strMonth){
			this.str = strMonth;
		}
		
		public String getStr() {
			return str;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("RED : "  + ConstTest.RED);
		System.out.println("THREE : " + ConstTest.THREE);
		
		//Dead Code
//		if(ConstTest.RED == ConstTest.TWO) {
//			System.out.println("...........");
//		}else { 
//			System.out.println("*********");
//		}
		
		//아래의 mycol1과 mycol2는 같다
		Color mycol1 = Color.valueOf("GREEN");
		Color mycol2 = Color.GREEN;
		
		Count mycnt = Count.THREE;
		System.out.println("mycol1 : " + mycol1.name());
		System.out.println("mycnt : " + mycnt.name());
	
		System.out.println("mycol1의 ordinal: " + mycol1.ordinal());
		System.out.println("mycnt의 ordinal: " + mycnt.ordinal());
	
//		// 서로 다른 타입의 열거형 이기때문에 비교 불가능  
//		if(Color.RED == Count.THREE) {
//			
//		}
		
		//if문 비교
		if(mycol1 == Color.BLUE) {
			System.out.println("같다.");
		}else {
			System.out.println("다르다.");
		}
		
		switch (mycol2) {
		case RED:
			System.out.println("빨강");
			break;
		case BLUE:
			System.out.println("파랑");
			break;
		case GREEN:
			System.out.println("초록");
			break;
		}
		switch(mycnt) {
		case ONE:
			System.out.println("하나");
			break;
		case TWO:
			System.out.println("둘");
			break;
		case THREE:
			System.out.println("셋");
			break;
		}
		
		System.out.println("----------------------------------");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : "+ ss.name());	//변수명 반환
		System.out.println("ordinal : "+ ss.ordinal());  //0번째 선언
		System.out.println("str : " + ss.getStr());		//변수의 값을 반환
		System.out.println();
		
		//Enum의 이름.values( ) => 모든상수를 배열로 반환한다. 
		for (Season show : Season.values()) {
			System.out.println(show.name() + " == " + show + "--> " + show.getStr());
		}
		System.out.println();
		
		for (Color show : Color.values()) {
			System.out.println(show.name() + "의 선언 순서 " + show.ordinal() + "번째 선언됨");
		}
		
	}
}

enum Test{
	
}