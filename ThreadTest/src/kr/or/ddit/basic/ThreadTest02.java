package kr.or.ddit.basic;


public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램
		//Thread를 사용하는 방법
		
		// 방법1
		// Thread클래스를 상속한 class를 작성한 후 이 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start() 메서드를 호출해서 실행한다.
		Mythread1 th1 = new Mythread1();
		th1.start();
		
		// 방법2
		// Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
		// 					이 인스턴스를 Thread 객체의 인스턴스를 생성할 때
		//					생성자의 매개변수로 넘겨준다.
		//					이 때 생성된 Thread 객체의 인스턴스의 start( )메소드를 호출한다.
		MyRunner r1 = new MyRunner();
		Thread th2 = new Thread(r1);
		th2.start();
		
		//방법3
		// 익명클래스를 이용하는 방법 (한번만 만들어서 쓸때 사용함)
		//	Runnable인터페이스를 구현한 익명클래스를
		//  Thread 인스턴스를 생성할 때 매개변수로 넘겨준다.

		
//		Runnable r = new Runnable  자동완성하면 만들어짐
//		Runnable r = new Runnable() {
//			
//			@Override
//			public void run() {
//				
//			}
//		};
//		Thread th3 = new Thread(r);
//		th3.start();
		
		Thread th3 = new Thread(new Runnable() {
			
			@Override 
			public void run() {
				for (int i = 1; i < 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}//run
		});
		th3.start();
		//Thread는 scheduler가 관리한다.
		
		System.out.println("Main 메소드 퇴근~!!! ");
	}//main

}//class

//방법1
class Mythread1 extends Thread{
	
	@Override
	public void run() {
		//이 run( ) 메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) 메서드는 주어진 시간동안 작업을 잠시 멈춘다
				// 시간은 밀리세컨드 1 / 1000 초를 의미한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
//방법2
class MyRunner implements Runnable{
			
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

