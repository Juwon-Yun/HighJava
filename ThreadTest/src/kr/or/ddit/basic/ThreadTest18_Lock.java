package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//은행의 입출금을 쓰레드로 처리하는 예제
//(동기화 처리 예제)


public class ThreadTest18_Lock {
		private int balance; 
		
		// Lock 객체 생성 => 되도록이면 private(외부접근X) final(변경X)로 만든다.
		private final Lock lock = new ReentrantLock();
		
		
		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}
		
		public void deposit(int money) {
			// Lock객체는 lock( )메소드로 락을 설정하고,
			// unlock( ) 메소드로 반드시 락을 해제한다.
			
			//락 설정 시작...
			lock.lock();
			//동기화 처리 부분
			balance += money;
			//동기화 해제 
			//해제를 않하고 넘어가면 deadlock 발생 후 다른 Thread에서 문제가 생긴다.
			lock.unlock();
		}
		
		public  boolean withdraw(int money) {
			lock.lock();
			boolean chk = false;
//			if(balance >= money ) {
//				//scheduler가 제어권을 다른 thread로 넘길수 있게하는 시간지연용
//				for(int i = 1; i<=100000000; i++) {}
//				balance -= money;
//				System.out.println("메소드 안에서 balance = " + getBalance());
////				System.out.println("메소드 안에서 balance = " + balance);
//				chk = true;
//			}else {
//				chk =  false;
//			}
//			
//			lock.unlock();
//			return chk;

			//try ~ catch 블럭을 사용할 경우에는 
			//unlock( ) 메소드 호출은 finally 블럭에서 하도록 한다.
			
			try {
					if (balance >= money) {
						//scheduler가 제어권을 다른 thread로 넘길수 있게하는 시간지연용
						for (int i = 1; i <= 100000000; i++) {
						}
						balance -= money;
						System.out.println("메소드 안에서 balance = " + getBalance());
						//				System.out.println("메소드 안에서 balance = " + balance);
						chk = true;
					} else {
						chk = false;
					 }
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return chk;
	} 

		public static void main(String[] args) {
		ThreadTest18_Lock acount = new ThreadTest18_Lock();
		acount.setBalance(10000);
		
		Runnable	test = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); 
				System.out.println("출금 성공???   result = " + result + ", 잔액 : " + acount.getBalance());
				
			}
		};
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
	}

}
