package kr.or.ddit.basic;
// 은행의 입출금을 쓰레드로 처리하는 예제
//  (동기화 처리 예제)


public class ThreadTest17 {
	//잔액이 저장될 변수
	private int balance; 

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금처리를 하는 메소드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금처리를 하는 메소드 (성공 : true, 실패 : false)
	public synchronized boolean withdraw(int money) {
//		public  boolean withdraw(int money) {
		if(balance >= money ) {
			//scheduler가 제어권을 다른 thread로 넘길수 있게하는 시간지연용
			for(int i = 1; i<=100000000; i++) {}
			balance -= money;
			System.out.println("메소드 안에서 balance = " + getBalance());
//			System.out.println("메소드 안에서 balance = " + balance);
			return true;
		}else
			return false;
	}
	
	public static void main(String[] args) {
		ThreadTest17 acount = new ThreadTest17();
		//잔액을 1만원으로 설정
		acount.setBalance(10000);
		
		// 익명 구현제로 쓰레드 구현
		Runnable	test = new Runnable() {
			
			@Override
			public void run() {
				//계좌에서 6000원 출금
				boolean result = acount.withdraw(6000); 
				System.out.println("출금 성공???   result = " + result + ", 잔액 : " + acount.getBalance());
				
			}
		};
		//---------------------------------------------- 익명구현체 끝
		
		//test 일을 하는 쓰레드 2개 구현
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
		
	}
}
