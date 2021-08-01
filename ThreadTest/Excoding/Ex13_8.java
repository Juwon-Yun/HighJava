package ThreadTest;


public class Ex13_8 {

	public static void main(String[] args) {
		ThreadEx8_1 th1 = new ThreadEx8_1();
		ThreadEx8_2 th2 = new ThreadEx8_2();
		
		th1.start();
		th2.start();
		
		delay(3*1000);
		
		System.out.println("<<Main 스레드 종료 >>");
	}

	static void delay(long millis) {
		try {
			Thread.sleep(3 * 1000);
//			th1.sleep(3*1000);  => Main스레드가 sleep 한다 
		} catch (InterruptedException e) {} //Exception의 자손으로 필수 예외처리 이다 
	}
}


class ThreadEx8_1 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("-");
		}
		System.out.println("<<th1 종료>>");
		super.run();
	}
}

class ThreadEx8_2 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("|");
		}
		System.out.println("<<th2 종료>>");
		super.run();
	}
}