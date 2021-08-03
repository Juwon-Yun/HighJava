package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 	10마리의 말들이 경주하는 프로그램을 작성하시오
 	
 	말은 Horse 라는 이름의 Thread class 로 작성하는데
 	이 class는 말이름(String), 현재위치(int), 등수(int) 를 멤버변수로 갖는다.
 	그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다.
 	Comparable 인터페이스 구현
 	
 	경기 구간은 1 ~ 50 구간으로 되어 있다.
 	
 	경기 중 중간 중간에 각 말들의 위치를 나타낸다.
 	예)
 	01번말  -->------------------------------------
 	02번말 ------->-------------------------------
 	...
 	10번말 ----->----------------------------------
 	
 	경기가  끝나면 등수 순으로 출력한다.
 */
public class ThreadTest14 {
	
	public static int Rank = 1;
 
	public static void main(String[] args) {
		List<Horse> list = new ArrayList<>();
		
		list.add( new Horse("01 번말"));
		list.add( new Horse("02 번말"));
		list.add( new Horse("03 번말"));
		list.add( new Horse("04 번말"));
		list.add( new Horse("05 번말"));
		list.add( new Horse("06 번말"));
		list.add( new Horse("07 번말"));
		list.add( new Horse("08 번말"));
		list.add( new Horse("09 번말"));
		list.add( new Horse("10 번말"));
		
		for (Horse show : list) {
			show.start();
		}
		
		for (Horse show : list) {
			try {
				show.join();
			} catch (InterruptedException e) {}
		}
		
		Collections.sort(list);
		System.out.println();
		System.out.println("=============경기 결과 =============");
		for (Horse show : list) {
			System.out.println("\t\t"+show.getHorseName() + "은\t" + show.getRank() + "등 입니다.");
			System.out.println("=================================");
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	int rank;
	int location;
	
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}
	
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public void run() {
		Random ran = new Random();
		for(int i = 0; i < 50; i++) {
			setLocation(i);
			System.out.print("\n" + horseName + "의 위치 "+getLocation() + " : ");
			for (int j = 0; j < i; j++) {
					System.out.print("-");
				}//for
				System.out.print(">");
				for(int j = 49; j  > i ; j--) {
					System.out.print("-");
				}
				System.out.println();
				try {
					Thread.sleep(ran.nextInt(300));
				} catch (InterruptedException e) {}
		}
		
		System.out.println();
		System.out.println(horseName +" 경주 종료" );
		
		setRank(ThreadTest14.Rank);
		ThreadTest14.Rank++;
	
	}

	@Override
	public int compareTo(Horse o) {
		//등수를 기준으로 오름차순 정렬
		if (rank > o.getRank()) {
			return 1;
		}else {
			return -1;
		}
	}
}
