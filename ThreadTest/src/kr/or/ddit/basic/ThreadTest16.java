package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject_method sObj = new ShareObject_method();
		
		TestThread th1 = new TestThread("test1", sObj);
		TestThread th2 = new TestThread("test2", sObj);
		
		th1.start();
		th2.start();
		
	}

}

class TestThread extends Thread{
	private ShareObject_method sObj;

	public TestThread(String name, ShareObject_method sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		
		//ShareObject의 add 메소드 10번 호출
		for(int i = 1; i<= 10; i++) {
			sObj.add();
		}
		
	}
}

//방법 1)  메소드에 동기화 설정을 한다.
class ShareObject_method{
	
	private int sum = 0;
	
		
	public synchronized void add() {
		int n = sum;
		
		n+=10;
		
		sum = n;
		// scheduler의 제어권 때문에 sum = n;은 실행이 안될수 있다.
		
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
	}
	
}

//방법2) 동기화 블럭으로 설정한다.
class ShareObject_block{
	
	private int sum = 0;
	
	// 동기화 하기
	//	synchronized (this) {
	//	}
	
	public  void add() {
		
		//this: 락이 걸릴 객체를 의미 
		synchronized ( this ) {
			int n = sum;
			
			n+=10;
			
			sum = n;
			// scheduler의 제어권 때문에 sum = n;은 실행이 안될수 있다.
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}