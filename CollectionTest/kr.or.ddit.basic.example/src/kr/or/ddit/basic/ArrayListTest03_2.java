package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest03_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<String> list = new ArrayList<>();
		System.out.println("5명의 별명을 입력하세요");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + 1 + "번째 별명: ");
			String name = sc.next();
			list.add(name);
		}

		List<String> kim = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String firstname = list.get(i).substring(0, 1);
			if (firstname.equals("김")) {
				kim.add(list.get(i));
			}
		}
		System.out.println(kim.toString());

		
		
		System.out.println("김씨성을 가진 사람들...");
		Scanner sc2 = new Scanner(System.in);
		List<String> nameList = new ArrayList<>();
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).charAt(0) == '김') {
				System.out.println(nameList.get(i));
			}
			if (nameList.get(i).substring(0, 1) == "김") {
				System.out.println(nameList.get(i));
			}
			if (nameList.get(i).indexOf("김") == 0) {
				System.out.println(nameList.get(i));
			}
			if (nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
			}
		}

		sc.close();
		sc2.close();
	}

}
