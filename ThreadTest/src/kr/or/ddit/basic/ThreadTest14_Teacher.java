package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;

public class ThreadTest14_Teacher {
	
	 // 각각의 말이 경기가  끝나면 1씩 증가
	// 즉 말의 등수를 구하는데 사용한다.
	public static int currentRank = 0; 
	
	public static void main(String[] args) {
		Horse_Teacher [ ] horses = new Horse_Teacher[]{
				new Horse_Teacher("01번말"), new Horse_Teacher("02번말"),
				new Horse_Teacher("03번말"), new Horse_Teacher("04번말"),
				new Horse_Teacher("05번말"), new Horse_Teacher("06번말"),
				new Horse_Teacher("07번말"), new Horse_Teacher("08번말"),
				new Horse_Teacher("09번말"), new Horse_Teacher("10번말"),
		};
		
		GameStateThread gst = new GameStateThread(horses);
		
		for (Horse_Teacher horse : horses) {
			horse.start();
		}
		
		//말들의 현재 위치를 출력하는 쓰레드 시작
		gst.start();
		
		try {
			for (Horse_Teacher h : horses) {
					h.join();
			}	
		gst.join();
		} catch (InterruptedException e) {}
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		// 등수를 오름차순으로 정렬하여 출력한다.
		Arrays.sort(horses);		//배열 정렬하기
		
		System.out.println("경기 결과");
		for (Horse_Teacher h : horses) {
			System.out.println(h);
		}
		
	}

}

class Horse_Teacher extends Thread implements Comparable<Horse_Teacher>{
	private String horseName;
	private int location;
	private int rank;
	
	
	
	public Horse_Teacher(String horseName) {
		super();
		this.horseName = horseName;
	}
	
	public String getHorseName() {
		return horseName;
	}


	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}


	public int getLocation() {
		return location;
	}


	public void setLocation(int location) {
		this.location = location;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다. ";
	}
	
	
	//등수의 오름차순
	@Override
	public int compareTo(Horse_Teacher horse) {
		return Integer.compare(rank, horse.getRank());	
	}
	
	//쓰레드에서 달리기 기능을 구현한다.
	@Override
	public void run() {
		Random ran = new Random();
		for(int i = 0; i < 50; i++) {
			
			this.location = i;	// 말의 현재 위치를 저장한다.
			
			try {
				Thread.sleep(ran.nextInt(500));
			} catch (InterruptedException e) {}
			
		}
	
	//한 마리의 말이 경주가 끝나면 등수를 구해서 저장한다.
		ThreadTest14_Teacher.currentRank ++;
		this.rank = ThreadTest14_Teacher.currentRank;
	}
}

// 경기 중 말의 위치를 표시하는 쓰레드
class GameStateThread extends Thread{
	//경기에 참가한 말들의 정보를 저장할 배열
	private Horse_Teacher [] horses;

	//생성자
	public GameStateThread(Horse_Teacher[] horses) {
		super();
		this.horses = horses;
	} 

	@Override
	public void run() {
		while(true) {
			//모든 말들의 경기가 종료되었을 때 이 쓰레드도 종료되게 한다.
			if(ThreadTest14_Teacher.currentRank == horses.length) {
				break;
			}
			//줄바꿈 
			for(int x = 0; x < 10; x++) {
				System.out.println();
				System.out.println();
			}

			for ( int i =0; i< horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j = 1; j<= 50; j++) {
					if(j == horses[i].getLocation()) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
			
			System.out.println(); //줄바꿈
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
		}
	}
}