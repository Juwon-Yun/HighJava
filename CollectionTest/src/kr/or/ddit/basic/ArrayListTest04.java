package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names2 = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 사람의 별명을 입력하시오.");
			String name = sc.nextLine();
			names2.add(name);
		}

		int max2 = 0;
		for (int i = 0; i < names2.size(); i++) {
			if (names2.get(max2).length() < names2.get(i).length()) {
				max2 = i;
			}
		}//max2에 최대 길이값을 갖고있는 인덱스 번호 부여
		
		System.out.println("제일 긴 별명들...");
		
		for (int i = 0; i < names2.size(); i++) {
			if (names2.get(max2).length() == names2.get(i).length()) {
				System.out.println(names2.get(i));
			}
		}//최대길이값과 같은 길이를 갖고있는 배열 인덱스 i 를 출력 
		sc.close();
	}
}
