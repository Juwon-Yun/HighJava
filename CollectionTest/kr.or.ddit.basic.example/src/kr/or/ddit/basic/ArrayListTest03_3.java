package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest03_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.println(i + "번째 사람의 별명을 입력하세요.");
			String name = sc.nextLine();
			int lang = name.length();
			if (i > 1) { // i가 1 이상이여야 비교가능하기때문에
				for (int j = 0; j < i - 1; j++) {
					if (list.get(j).length() == lang) {
						System.out.println("별명의 길이는 같은 수 없습니다. 다시입력하세요");
						name = sc.nextLine();
					}
				}
			}
			list.add(name);
		}
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(max).length() < list.get(i).length()) {
				max = i;
			}
		}
		System.out.println("제일 긴 별명: " + list.get(max));

		System.out.println("*******************************************************************");
		
		List<String> aliasList = new ArrayList<>();

		System.out.println("서로 다른 길이의 별명을 5번 입력하시오.");
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 별명 : ");
			String alias = sc.next();
			aliasList.add(alias);
		}

		// 제일 긴 별명이 저장될 변수를 선언하고 이 변수에는 List의 첫번째 데이터로 초기화 한다.
		//	String maxAlias = aliasList.get(0);
		// 제일 긴 별명이 저장된 index값이 저장될 변수를 선언하고 0으로 초기화 한다.
		int maxIndex = 0;

		for (int i = 1; i < aliasList.size(); i++) {
			//		if(maxAlias.length() < aliasList.get(i).length()){
			//			maxAlias = aliasList.get(i);
			//		}

			if (aliasList.get(maxIndex).length() < aliasList.get(i).length()) {
				maxIndex = i;
			}
		}

		//	System.out.println("제일 긴 별명 : " + maxAlias);
		System.out.println("제일 긴 별명 : " + aliasList.get(maxIndex));
		sc.close();
	}
}
