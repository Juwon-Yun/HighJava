package ThreadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HorseRaceThread {
	public static int horseRank = 1;
	
	public static void main(String[] args) {
		List<Horse> horse = new ArrayList<>();
		horse.add(new Horse("1번말"));
		horse.add(new Horse("2번말"));
		horse.add(new Horse("3번말"));
		horse.add(new Horse("4번말"));
		horse.add(new Horse("5번말"));
		horse.add(new Horse("6번말"));
		horse.add(new Horse("7번말"));
		horse.add(new Horse("8번말"));
		horse.add(new Horse("9번말"));
		horse.add(new Horse("10번말"));
	
		for (Horse show : horse) {
			show.start();
		}
		
		for (Horse show : horse) {
			try {
				show.join();
			} catch (InterruptedException e) {}
		}
			
		Collections.sort(horse);	
		System.out.println();
		System.out.println("===========경기 결과==========");
		for (Horse show : horse) {
			System.out.println(show.getHorseName() + "이 " + show.getRank() + "등 입니다.");
			System.out.println("============================");
		}
	}

}


class  Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	private int location;
	
	
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
		for(int i  = 0; i < 50;  i ++) {
			setLocation(i);
			System.out.print("\n" + horseName + "의 위치 " + getLocation() + " : ");
			for(int j = 0; j < i; j++) {
				System.out.print("-");
			}
			// j가 0부터 i값보다 작을떄까지  ++ 
			
			System.out.print(">");		//for문 두개 사이에  >를 넣어 >---, ->--, -->-- 이 출력되게함
			
			for(int j = 49; j >  i; j--) {
				System.out.print("-");
			}
			// j가 49부터  i값 보다 클때 까지  -- 
			
			try {
				Thread.sleep(ran.nextInt(300));
			} catch (InterruptedException e) {}
		}
		System.out.println();
		System.out.println("\t" + getHorseName() + "경주 종료" + " ");
		
		setRank(HorseRaceThread.horseRank);
		HorseRaceThread.horseRank++;
	}
	
	@Override
	public int compareTo(Horse run) {
		// 기존 Rank가 입력한 Rank값보다 크면 바꾸지않고 
		// 작으면 -1 리턴하여 바꾼다.
		if (this.rank > run.getRank()) {
			return 1;	// A > B 일 경우 1을 리턴하고 자리를 바꾸지않는다 
		}else {
			return -1;		// A  < B 인경우 -1을 리턴하고 자리를  바꾼다 
		}
		
	}
	
}