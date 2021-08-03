package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadTest19 {
	/*
	 	Vector, Hashtable등과 같이 예전부터 존재하던 Collection 객체들은 
	 	내부에 동기화 처리가 되어있다.
	 	
	 	그런데, 최근에 새로 구성된 Collention들은 동기화 처리가 되어있지 않다.
	 	동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에
	 	사용해야한다.
	 	
	 	동기화 처리시: Collections의 정적 메소드 중에 synchronized로 시작하는 메소드를 사용한다.
	 	
	 */

	//동기화 처리가 되는 Vector
//	private static Vector<Integer> vec = new Vector<>();
	
	//동기화 처리가 되지 않은 List
//	private static List<Integer> list1 = new ArrayList<>();
	
	//동기화 처리가 된 List
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {

		//---------------------------------------------
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for(int i = 0; i <10000; i++) {
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		//익명구현체 끝--------------------------------
		
		Thread [ ] thArr = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)
		};
		
		long startTime = System.currentTimeMillis();
		
		for (Thread th : thArr) {
			th.start();
		}
		
		for (Thread th : thArr) {
			try {
				th.join();
			} catch (InterruptedException e) {}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("처리 시간(ms) : " + (endTime - startTime));
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
		
	}
}

