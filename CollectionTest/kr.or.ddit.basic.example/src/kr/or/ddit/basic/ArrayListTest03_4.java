package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest03_4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> names2 = new ArrayList<>();
		for(int i = 1; i <= 5; i++){
			System.out.println(i + "번째 사람의 별명을 입력하시오.");
			String name = sc.nextLine();
			names2.add(name);
		}
			
		int max2 = 0;
		for(int i = 0; i < names2.size(); i++){
			if(names2.get(max2).length() < names2.get(i).length()){
				max2 = i;
			}
		}
			
		for(int i = 0; i < names2.size(); i++){
			if(names2.get(max2).length() == names2.get(i).length()){
				System.out.println(names2.get(i));
			}
		}
		
		System.out.println("*************************************************");
		
		ArrayList<String> aliasList = new ArrayList<>();
		
		System.out.println("별명을 5번 입력하시오.");
		for(int i = 1; i <= 5; i++){
			System.out.println(i + "번째 별명 : ");
			String alias = sc.next();
			aliasList.add(alias);
		}
			
		//제일 긴 별명의 길이가 저장될 변수를 선언하고 이 변수는 List의 첫번째 데이터의 길이로 초기화한다.
		int maxLength = aliasList.get(0).length();
			
		for(int i = 1; i < aliasList.size(); i++){
			if(maxLength < aliasList.get(i).length()){
				maxLength = aliasList.get(i).length();
			}
		}
			
		System.out.println("제일 긴 별명들...");
		for(int i = 0; i < aliasList.size(); i++){
			if(aliasList.get(i).length() == maxLength){
				System.out.println(aliasList.get(i));
			}
		}
		sc.close();
	}
}