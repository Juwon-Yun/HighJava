package kr.or.ddit.basic;

public class ThreadTest05 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();
		
		System.out.println("th1의 우선 순위 : " +th1.getPriority());
		System.out.println("th2의 우선 순위 : " +th2.getPriority());

		th1.setPriority(10);
		th2.setPriority(1);
		
		th1.start();
		th2.start();

		System.out.println("th1의 우선 순위 : " +th1.getPriority());
		System.out.println("th2의 우선 순위 : " +th2.getPriority());
	
	}
}

class UpperThread extends Thread{

	@Override
	public void run() {
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.print(c + " ");
			for (long i = 1; i <=10_000_000_000L; i++) {}
		}
	}
}

class LowerThread extends Thread{
	
	@Override
	public void run() {
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.print(c + " ");
			for (long i = 1; i <=10_000_000_000L; i++) {}
		}
	}
}