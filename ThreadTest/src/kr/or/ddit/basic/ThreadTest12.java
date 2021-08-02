package kr.or.ddit.basic;
/*
 	Thread의 stop( ) 메서드를 호출하면 쓰레드가 바로 멈춘다. 
 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 
 	나중에 실행되는 프로그램에 영향을 줄 수 있다.
 	그래서 stop( ) 메서드는 비추천으로 되어 있다.
 */
public class ThreadTest12 {

	public static void main(String[] args) {
		/*ThreadStopEx1 tse = new ThreadStopEx1();
		tse.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {}
		
//		tse.stop(); // 강제종료. deprecated 쓰지마라는 표시
		tse.setStop(true);*/
		
		//interrupt ( ) 메소드를 이용한 쓰레드 멈추기
		ThreadStopEx2 tse2 = new ThreadStopEx2();
		tse2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		tse2.interrupt();
	}
}

class ThreadStopEx1 extends Thread{
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
		public void run() {
		while (!stop) {
//			for (int i = 0; i < 5; i++) {
//				for(int j = 0; j<= i; j++) {
//					System.out.print("*");
//				}
//				System.out.println();
//			}
			System.out.println("쓰레드 처리중...");
		}
		System.out.println("자원 정리중....");
		System.out.println("실행 종료...");
	}
}

//interrupt( )메소드를 이용하여 Thread를 멈추게 하는방법
class ThreadStopEx2 extends Thread{
	@Override
	public void run() {
		// 방법1 => inturrupt( )메소드와 sleep( ) 메소드를 이용하는 방법 
		try {
			
		while(true) {
			System.out.println("실행중....");
			Thread.sleep(1);
			}
		}catch (InterruptedException e) {
			System.out.println("인터럽트 익셉션 발생함");
		}
		
		while(true) {
//			System.out.println("Thread 실행중...");
//			// interrupt ( )메소드가 호출되었는지 검사한다.
//			//검사방법1 => Thread의 인스턴스 메소드인 isInterrupted( ) 메소드를 이용
//			//			isInterrupted( ) 메소드는 interrupt( )메소드가 호출되면 true를 반환한다.
//			if(this.isInterrupted()) {
//				System.out.println("isInterrupted( ) 출력 : " + this.isInterrupted());
//				break;
//			}
//			
			//검사 방법2 => Thread의 정적 메소드인 interrupted( ) 메소드 이용하기
			//			interrupted( ) 메소드도  interrupt( ) 메소드가 호출되면
			//			true를 반환한다.
			if(Thread.interrupted()) {
				System.out.println("Thread의 정적 메소드 interrupted 호출 값 : " + Thread.interrupted());
				break;
			}
			
			
		}
		System.out.println("자원 정리중...");
		System.out.println("실행 종료.");
		}
}

class ThreadStopEx3 extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println("Thread 실행중...");
			// interrupt ( )메소드가 호출되었는지 검사한다.
			//검사방법1 => Thread의 인스턴스 메소드인 isInterrupted( ) 메소드를 이용
			//			isInterrupted( ) 메소드는 interrupt( )메소드가 호출되면 true를 반환한다.
			if(this.isInterrupted()) {
				System.out.println("isInterrupted( ) 출력 : " + this.isInterrupted());
				break;
			}
			
			//검사 방법2 => Thread의 정적 메소드인 interrupted( ) 메소드 이용하기
			//			interrupted( ) 메소드도  interrupt( ) 메소드가 호출되면
			//			true를 반환한다.
			if(Thread.interrupted()) {
				System.out.println("Thread의 정적 메소드 interrupted 호출 값 : " + Thread.interrupted());
				break;
			}
			
			
		}
		System.out.println("자원 정리중...");
		System.out.println("실행 종료.");
	}
}
class ThreadStopEx4 extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println("Thread 실행중...");
			//검사 방법2 => Thread의 정적 메소드인 interrupted( ) 메소드 이용하기
			//			interrupted( ) 메소드도  interrupt( ) 메소드가 호출되면
			//			true를 반환한다.
			if(Thread.interrupted()) {
				System.out.println("Thread의 정적 메소드 interrupted 호출 값 : " + Thread.interrupted());
				break;
			}
			
			
		}
		System.out.println("자원 정리중...");
		System.out.println("실행 종료.");
	}
}

















