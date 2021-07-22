package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에
 *  		 이들중 '김' 씨 성의 이름을 모두 출력하시오.
 *  		 (입력은 Scanner를 이용한다.)
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<String> strlist = new ArrayList<>();
		System.out.println("5명의 이름을 입력하세요");
		for (int i = 1; i < 6; i++) {
			System.out.println(i + "번째 이름: ");
			String name = sc.next();
			strlist.add(name);
		}
		
		System.out.println();
		System.out.println("김씨 성을 가진 사람들");
		for (int i = 0; i < strlist.size(); i++) {
			String name = strlist.get(i);// i번째 이름
			if(name.substring(0,1).equals("김")) 
			{
				System.out.println(name);
			}
			if(name.charAt(0) == '김') {
				System.out.println(name);
			}
			if(name.indexOf("김") == 0) {
				System.out.println(name);
			}
//			if(name.contains("김") == true) { //있는지 없는지 검사만 한다.
//				System.out.println(name);
//			}
			if(name.startsWith("김")) { // ( )값이 true 면 if문을 실행.
				System.out.println(name);
			}
		}
//		System.out.println("strlist의 값: " +  strlist);
//		System.out.println();
//		
//		String arr = strlist.toArray().toString();
//		
//		List<String> strlist2 = new ArrayList<>();
//		
//		for (int j = 0; j < strlist.size(); j++) {
//			if(arr.substring(0).contains("김") == true) {
//				strlist2.add(strlist.get(j));
//			}
//		}
//		
//		for (Object show : strlist2) {
//			System.out.println(show);
//		}
//		System.out.println("===============================");
//		
//		for (int i = 0; i < strlist.size(); i++) {
//			String str = strlist.get(i);
//			System.out.println(str);
//		}
//		
//		
		sc.close();
	}
}
