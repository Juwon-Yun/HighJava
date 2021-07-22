package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorTest01 {
	// 자바고급 담당 : 이성엽
	public static void main(String[] args) {
		
		//객체 생성
		Vector<Object> v1 = new Vector<Object>();
		System.out.println("처음 크기: " + v1.size());
		
		//데이터추가 : add(추가할 데이터)<--객체여야함
		//반환값 : 성공(true), 실패(false) 
		v1.add("aaa"); //래퍼클래스 
		v1.add(new Integer(111)); // 자료형 객체 설정 
		v1.add(123); //auto boxing 
		v1.add('a');
		v1.add(3.14);
		boolean r = v1.add(true);
		
		System.out.println("현재 크기: " + v1.size());
		System.out.println("반환값: "+ r );
		System.out.println("v1 => " + v1);
		
		// 데이터 추가2 : addElement(추가할 데이터)
		// => 이전 버전에서부터 지원하는 메소드
		// => 이전 버전으로 작성된 프로그램을 위해서 남겨놓은 메소드
		v1.addElement("CCC");
		System.out.println(" v1 => "+ v1);
		
		// 데이터 추가2 : add(index, 데이터)
		// => 'index' 번째에 '데이터'를 끼워 넣는다.
		// => 'index' 는 0번부터 시작한다.
		// => 반환값은 없다.
		v1.add(1, "kkk");
		System.out.println(" v1 => "+ v1);
		
		// 데이터 꺼내오기: get(index);
		// => 'index' 번째 데이터를 가져온다.
//		int data = Integer.parseInt(v1.get(3).toString());
		int data = (int)(v1.get(3)); //auto unboxing
		System.out.println("3번째 데이터: "+ data);
		
		// 데이터 변경하기 : set(index, 새로운 데이터);
		// => 'index'번째의 데이터를 '새로운 데이터'로 덮어쓴다.
		// => 반환값 : 원래의 데이터
		String temp = (String)v1.set(0, "changed data");
		System.out.println("v1 => " + v1);
		//**********************안바뀜*******************
		System.out.println("반환값 : " + temp);
		//데이터 삭제하기 : remove(index);
		// => 'index' 번째의 데이터를 삭제한다.
		// => 반환값 : 삭제된 데이터
		temp = (String)v1.remove(0);
		System.out.println("v1 => "+v1);
		System.out.println("삭제된 데이터 : " + temp);
		
		// 데이터 삭제하기2 : remove(삭제할 데이터);
		// => '삭제할 데이터'를 찾아 삭제한다.
		// => 삭제할 데이터가 여러개면 앞에서부터 찾아서 삭제한다.
		// => 반환값 : 삭제 성공(ture), 삭제 실패(false)
		// => 삭제할 데이터가 '정수형' 이거나 'char형'일 경우에는 반드시
		//  	 변환해서 사용해야 한다.
		v1.remove("CCC");
		System.out.println("삭제후 : " + v1);
		
//		v1.remove(2);
		v1.remove(new Integer(123));
		System.out.println("삭제후 : " + v1);
		
		v1.remove(new Character('a')); //작은 따옴표 안의 문자의 코드값으로 (숫자) 저장되기 때문에 
		System.out.println("삭제후 : " + v1);
		
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("삭제후 : " + v1);
		
		// 전체 데이터 삭제 : clear();
		v1.clear();
		System.out.println("삭제후 : " + v1);
		
		//
		/*
		 * 제네릭 타입(Generic Type) = 클래스 내부에서 사용할 데이터의 타입
		 * 							     				외부에서 지정하는 방식
		 * 			=> 객체를 선언할 때  < >안에 그 객체가 사용할 데이터의 타입을
		 *               정해주는것을 말한다.
		 *  		=> 이런식으로 객체를 선언하게 되면 다른 종류의 데이터를 저장할 수 없다.
		 *  		=> 이 때 제네릭으로 선언될 수 있는 데이터 타입을 클래스형이어야 한다.
		 *  			  (int => Integer, boolean => Boolean, char => Charactor등으로 대체해서
		 *  				 사용해야 한다.)
		 *  	    => 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이
		 *  			  필요없다.
		 */
		
		List<String> v2  = new ArrayList<>();
		v2.add("안녕하세요");
		//v2.add(123);  // Exception: 다른 종류의 데이터를 저장할 수 없다.
		
		String temp2 = v2.get(0); //형 변환 없이 자료를 꺼내올 수 있다.
		System.out.println(temp2);
//		List<List<String>> v3 = new ArrayList<>();
		
		v2.clear();
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		List<String> v3 = new ArrayList<>();
		v3.add("BBB");
		v3.add("EEE");
		
		System.out.println("v2 = " + v2);
		System.out.println("v3 = " + v3);
		
		v2.remove(v3.get(1));
		System.out.println("v2 => " + v2);
		// 데이터 삭제하기 : removeAll(Collection 객체)
		// => 'Collection 객체'가 가지고 있는 데이터를 찾아서 모두 삭제한다.
		// => 반환값 : 삭제성공(true),  삭제실패(false)
		
		v2.removeAll(v3);
		System.out.println("v2 => " + v2);
		v2.clear();
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		System.out.println(v2);
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문 사용)
		
		int i = 0;
		System.out.println("===============================");
		for (String show : v2) {
			System.out.println(" "+ i+" " + "번째 자료: " + show);
			i++;
		}
		System.out.println("===============================");
		
		
	}
}
