package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class setTest {

	public static void main(String[] args) {
		/*
		 -List와 Set의 차이점
		 1. List 
		 	-데이터의 순서 (index)가 있다.
		 	-중복되는 데이터를 저장할 수 있다.
		 2.Set
		 	-데이터 순서가 (index)가 없다.
		 	-중복되는 데이터를 저장할 수 없다.
		 */
		
		Set<Object> hs1 = new HashSet<>();
		//set에 데이터를 추가할 때도 add() 메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set의 개수: " + hs1.size());
		System.out.println("Set의 데이터 출력: " + hs1);
		
		//Set에 중복되는 데이터를 추가하면 false를 반환하고
		//데이터는 추가되지 않음.
		
		int is;
		boolean isAdd = hs1.add("FF");
		if(isAdd == false) {
			is = 1;
		}else
			is = 0;
		System.out.println(isAdd);
		System.out.println(is);
		
		
		boolean isAdd2 = hs1.add("CC");
		System.out.println("중복될 때: " + isAdd2);
		System.out.println("Set데이터: " + hs1);
		System.out.println();
		
		//Set의 데이터 수정하려면 수정하는 명령이 따로 없기 때문에
		//해당 자료를 삭제한 후 새로운 데이터를 추가해주어야함
		
		//삭제하는 메서드: remove(삭제할데이터)
		//반환값: 삭제성공(true)  삭제실패(false)
		//자료 전체 삭제하는 메서드: clear();
		
		//"FF"를 "EE"로 수정하기
		
		hs1.remove("FF");
		// hs1의 데이터 : [DD, AA, CC, BB, 1, 2, 3]
		hs1.add("EE");
		// hs1의 데이터 : [DD, AA, CC, BB, EE, 1, 2, 3]
//		hs1.clear();
		// hs1의 데이터 : []
	
		/*
		 	-Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를
		 	  하나씩 불러올 수 없다.
		 	  데이터를 하나씩 가져오기 위해서는 Iterator로 처리해야 한다.
		 	  
		 	  Iterator 객체 생성
		 	  Iterator: 인터페이스, hasNext()와 next()를 추상메서드로 가지고 있음
	
		 */
		//변환
		Iterator<Object> it = hs1.iterator();
		
		//Iterator에서 데이터를 하나씩 꺼내오기
		//hasNext(): 포인터 다음 위치에 데이터가 있으면  true, 없으면 false를 반환
		//next(): 포인터를 다음 자료위치로 이동하고, 이동한 위치의 자료를 반환
		
		//hs1에 있는 자료들을 전부 출력함
		while(it.hasNext()) {
			System.out.println(it.next());
		}

		//Set 데이터를 하나씩 꺼내오는 방법2
		for (Object show : hs1) {
			System.out.println(show);
		}
			
		
		
		//우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자
		//번호는  1번부터 24번까지 있고, 추첨할 인원은 3명이다.
		//당첨자 출력하기
		
		//35~46
		//ran.nextInt(46) + 35;
		
		Set<Integer> intRan = new HashSet<>();
		Random ran = new Random();
		int num = ran.nextInt(24)+1;
		
		while(intRan.size() < 3) {
//			intRan.add((int)Math.random()*24 +1);
			intRan.add(num);
		}
		System.out.println("당첨자 번호: " + intRan);
	
		
		//Set형의 자료를 List형으로 변환하기
		
		List<Integer> intRanList = new ArrayList<>(intRan);
		
		System.out.println("일반 for문");
		for (int i = 0; i < intRanList.size(); i++) {
			System.out.println(intRanList.get(i));
		}
		
		System.out.println("향상된 for문");
		for (Integer show : intRanList) {
			System.out.println(num + "  "+  show);
		}
		
		
	}
}
