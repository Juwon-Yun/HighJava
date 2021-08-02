package kr.or.ddit.basic;

// yield( ) 메소드 예제
public class ThreadTest11 {

	public static void main(String[] args) {
		YieldThread1 yh1 = new YieldThread1("1번 쓰레드");
		YieldThread1 yh2 = new YieldThread1("2번 쓰레드");
		yh1.start();
		yh2.start();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {}
		System.out.println("=======================================1111111111111111===");
		yh1.work = false;
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {}
		System.out.println("=======================================222222222222===");
		
		yh1.work  = true;
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {}
		System.out.println("=======================================333333333333===");
		
		yh1.stop = true;
		yh2.stop = true;
		
	}
}

class YieldThread1 extends Thread {
	public boolean stop = false;
	public boolean work = true;
	
	//생성자
	public YieldThread1(String name) {
		super(name);
	}


	@Override
	public void run() {
//		for(int i = 1; i <= 100; i++) {
//			System.out.println("YeildTest1" + i);
//			yield(); 		//양보하기
//		}
		while(!stop) {
			if(work) {
				//조건에 만족할때 하는일
				System.out.println(getName() + "작업중....");
			}else {
				//조건에 만족하지않으면 다른 쓰레드에 제어권 양보 (Yield)
				System.out.println(getName() + "양보...양보...양보...양보...양보...");
				Thread.yield();
			}
		}
		System.out.println(getName() + "쓰레드 종료...");
	}
}
