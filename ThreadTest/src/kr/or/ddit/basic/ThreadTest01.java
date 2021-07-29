package kr.or.ddit.basic;

public class ThreadTest01 {
	public static void main(String[] args) {
		// Single Thread Program
		for (int i = 1; i < 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 1; i < 200; i++) {
			System.out.print("$");
		}
	}//main
}//class
