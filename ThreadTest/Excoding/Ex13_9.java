package ThreadTest;

import javax.swing.JOptionPane;

public class Ex13_9 {

	public static void main(String[] args) {
		ThreadEx9_1 th1  = new ThreadEx9_1();
//		th1.setDaemon(true);
		th1.start();
		
		System.out.println("isInterrupted() 의 값 : "+ th1.isInterrupted());
		
		String input = JOptionPane.showInputDialog("아무거나 입력하세요");
		
		System.out.println("입력하신 값은 " + input + "입니다.");
		
		th1.interrupt();
//		th1.interrupt()를 호출하면 interrupted상태가 true가 된다.
		
		System.out.println("isInterrupted() 의 값 : "+ th1.isInterrupted()); 
		//th1가 interrupted() 된적이 있냐 라고 물어보는것
		
		//main Thread가 interrupt되었는지 확인
		//interrupted()는 Static 메소드 이기때문에 th1.interrupted()가 아닌 메인스레드의 
		//Thread.interrupted() 로 작성해야 확인할 수 있다.
		System.out.println("interrupted() 의 값 : " + Thread.interrupted());
	}

}

class ThreadEx9_1 extends Thread{
	public void run() {
		int i = 10;
		while( i != 0  && !isInterrupted()) {
			System.out.println(i--);
			for(long x = 0; x < 250000000L; x++);  //시간 지연 
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {}
		}
		System.out.println("ThreadEx9_1의 상태 =====================");
		System.out.println("isInterrupted() 의 값 : "+ this.isInterrupted()); 
		System.out.println("isInterrupted() 의 값 : "+ this.isInterrupted()); 
		
//		isInterrupted()와 달리 interrupted()는 interrupted라는 상태 변수를 false로 초기화한다.
		System.out.println("interrupted() 의 값 : " + Thread.interrupted());
		System.out.println("interrupted() 의 값 : " + Thread.interrupted());
		System.out.println("카운트가 종료되었습니다.");
	}
}
