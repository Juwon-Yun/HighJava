package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<String> strlist = new ArrayList<>();
		System.out.println("5명의 별명을 입력하세요");
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 별명: ");
			String name = sc.next();
			strlist.add(name);
		}
		List<String> strlist2 = new ArrayList<>();

		for (int i = 0; i < strlist.size(); i++) {
			System.out.println(i + "번째 별명의 길이: " + strlist.get(i).length());
			for (int j = 0; j <= i; j++) {
				if (strlist.get(i).length() > strlist.get(j).length()) {
					strlist2.add(strlist.get(i));
				} else if (strlist.get(i).length() == strlist.get(j).length()) {
					System.out.println(i + "번째 별명과 " + j + "번째 별명 길이가 같습니다");
				}
			}
		}
		for (String show : strlist2) {
			System.out.println(show);
		}
		sc.close();
	}
}
