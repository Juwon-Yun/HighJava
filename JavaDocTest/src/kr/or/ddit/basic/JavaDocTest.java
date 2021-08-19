package kr.or.ddit.basic;
// javaDoc 파일 만들기 예제

/**
 * 
 * @author 홍길동
 * @version 1.0
 * 
 * <p>
 * 	파일명 : JavaDocTest.java<br>
 * 	설	  명 : JavaDoc문서 작성을 위한 연습용 Interface<br><br>
 *  
 *  수정 이력<br>
 *  ----------------------------------------------------------<br>
 *  수정 일자 : 2021-08-19
 *  수정한 사람 : 홍길동<br>
 *  수정 내용 : 최초 생성<br>
 *  ----------------------------------------------------------<br>
 * </p>
 *
 */
public interface JavaDocTest {
	/**
	 * 메소드명 : methodTest
	 * 설		명 : 반환값이 없는 메소드
	 * @param a 첫번쨰 매개변수(정수형)
	 * @param b 두번째 매개변수(정수형)
	 */
	public void methodTest(int a, int b);
	
	
	/**
	 * 메소드명 : methodAdd
	 * 설		명	: 반환값이 있는 메소드
	 * @param x 정수형 데이터가 저장될 첫번째 변수
	 * @param y 정수형 데이터가 저장될 두번째 변수
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodAdd(int x, int y);
	
	
	/**
	 * 메소드명 : methodSub
	 * 설	    명 : 반환값과 매개변수가 없는 메소드 
	 */
	public void methodSub();
}













