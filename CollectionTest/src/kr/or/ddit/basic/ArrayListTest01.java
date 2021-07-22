package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		
	//ArrayList의 기본 사용법은 Vector와 같다.
	//비동기화 
	
	ArrayList<Object>  list1 = new ArrayList<>();
	
	list1.add("aaa");
	list1.add("bbb");
	list1.add("ccc");
	list1.add(true);
	list1.add(123);
	list1.add(3.14);

	System.out.println("list1 => " + list1);
	System.out.println("size => " + list1.size());
	
	System.out.println("1번째 데이터: " + list1.get(1));
	
	list1.add(3, "zzz");
	System.out.println("list1 => " + list1);
	
	String temp = (String)list1.set(3, "안녕하세요");
	//변경되기 전 데이터값 반환
	System.out.println("temp => "+temp);
	System.out.println("list1 => " + list1);
	
	
	//삭제도 같다
	list1.remove(3);
	System.out.println("삭제 후: " + list1);
	//데이터를 지정해서 삭제
	list1.remove("bbb");	
	System.out.println("삭제 후: " + list1);
	
	//제네릭을 사용할 수 있다.
	ArrayList<String> list2 = new ArrayList<>();
	
	list2.add("AAA");
	list2.add("BBB");
	list2.add("CCC");
	list2.add("DDD");
	list2.add("EEE");
	list2.add("DDD");
	
	for (int i = 0; i < list2.size(); i++) {
		System.out.println(i + "번째: "+ list2.get(i));
	}
	System.out.println("==========================");
	int j= 0;
	for (String show : list2) {
		System.out.println(j + "번째: " + show);
		j++;
	}
	System.out.println("==========================");
	
	// contanis(비교객체) => List에 '비교객체'가 있으면 true 없으면 false를 반환한다.
	
	System.out.println("DDD값 존재여부: " + list2.contains("DDD"));
	System.out.println("ZZZ값 존재여부: " + list2.contains("ZZZ"));
	
	//indexOf(비교객체)
	//  =>List에 '비교객체'가 있으면 '비교객체' 가 있는 index 값을 반환하고
	//		 없으면 -1을 반환한다.
	
	System.out.println("AAA값 존재여부: " + list2.indexOf("AAA"));
	System.out.println("ZZZ값 존재여부(없으면 -1, 있으면 인덱스값) : "+list2.indexOf("ZZZ"));
	
	// toArray() => List안에 데이터를 배열로 변환하여 반환한다.
	// 			=> 기본적으로 Object형 배열로 반환한다.
	
	Object[] objarr = list2.toArray();
	//String 문자형인 list2를 Object형 objarr 배열로 반환 
	System.out.println("배열의 개수 " + objarr.length);
	System.out.println("List list2 개수 " + list2.size());
	
	// toArray(new 제네릭타입[0]);
	// => 제네릭타입의 배열로 변환한다.
	
	Object[] objarr2 = list2.toArray(new String[0]);
	System.out.println("배열의 개수 " + objarr2.length);
	
	for (int i = 0; i < objarr2.length; i++) {
		System.out.println( i + "번째: " + objarr2[i]);
	}
	System.out.println("===========================");
	for (Object show : objarr2) {
		System.out.println("번째" + show);
	}
	System.out.println("===========================");
	
	
	
	
	
	}
}
