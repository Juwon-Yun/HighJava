package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTest {

	public static void main(String[] args) {
		/*
		 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
		 		  컴퓨터의 숫자는 난수를 이용하여 구한다.
		 		  (스트라이크 S, 볼은 B로 나타낸다.)
		 예시)
		 		  컴퓨터의 난수 ==> 9 5 7
		 
		 실행예시)
		 		숫자입력 => 3 5 6
		 		3  5  6  => 1S 0B
		 		숫자입력 => 7 8 9
		 		7  8  9  => 0S 2B
				숫자입력 => 9 7 5
				9  7  5  => 1S 2B
				숫자입력 => 9 7 5
				9  5  7 => 3S 0B
		 
		 		축하합니다.
		 		당신은 4번째만에 맞췄습니다.
		 */
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		int num;
		int strike=0;
		int ball = 0;
		

		Set<Integer> comSet = new HashSet<>();
		while(comSet.size() < 3) {
				num = ran.nextInt(9)+1;
				comSet.add(num);
			}
		List<Integer> listcomSet = new ArrayList<>(comSet);
		
		System.out.print("컴퓨터의 난수 ==> ");
		for (Integer show : listcomSet) {
			System.out.print(show + "  ");
		}
		System.out.println();
		
		
		int count = 0;
			
		base: while (true) {
			List<Integer> userList = new ArrayList<>();
			System.out.print("숫자입력 => ");
			for (int j = 0; j < 3; j++) {
				userList.add(sc.nextInt());
			}

			for (int i = 0; i < listcomSet.size(); i++) {
				for(int j = 0; j < listcomSet.size(); j++) {
					if (userList.get(i) == listcomSet.get(j)) {
						if(i==j) {
							strike++;
						}else {
							ball++;
						}
					}
				}
			}
//			for (Integer show : userList) {
//				System.out.print(show + "  ");
//			}
			System.out.println(userList.get(0) + " " + userList.get(1) + " "+userList.get(2)  + "==> " + strike + "S " + ball + "B");
			count++;
			
			if(strike == 3) {
				System.out.println("축하합니다.");
				System.out.println("당신은 " + count + "번째만에 맞췄습니다.");
				break base;
			}
			strike = 0;
			ball = 0;
		}
	
		sc.close();
	}
}
