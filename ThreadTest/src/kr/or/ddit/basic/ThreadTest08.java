package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest08 {
	public static void main(String[] args) {
		Thread th1 = new Thread(new DataInput());
		Thread th2 = new Thread(new CountDown());
		
		th1.start();
		th2.start();
		
	}
	
}

class DataInput implements Runnable {
	//입력 여부를 확인하기 위한 변수 선언
	//쓰레드에 공통으로 사용할 변수(입력이 완료되면 true 값이 저장된다.)
	public static boolean inputCheck;
	// static 변수는 자동으로 default값으로 초기화됨 
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		//입력이 완료되어 값을 true로 변환한다.
		inputCheck = true;
		
		System.out.println("입력한 값 : " + str);
	}
}

// 카운트 다운을 담당하는 쓰레드
class CountDown implements Runnable{

	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			//입력이 완료되었는지 여부 검사하기
			//완료되었으면 쓰레드 종료
			if(DataInput.inputCheck == true) {
				System.out.println("시간안에 입력이 완료되어 메소드를 끝냅니다.(return)");
				return; // run()메소드가 종료되면 Thread도 종료된다.
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다");
		System.exit(0);
	}
	
}