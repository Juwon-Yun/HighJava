package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class ThreadTest09_02 {
	String strcom;
	String input;

	public static  void main(String[] args) {
		new ThreadTest09_02().start();
	}
	
	private void start() {
		System.out.println("========================");
		System.out.println("======가위바위보 시작=======");
		System.out.println("========================");
		comRandom();

		Thread th1 = new Thread(new RSPCountDown());
		th1.start();
		
		showresult();
	}

	private  void showresult() {
		
		input = JOptionPane.showInputDialog("가위, 바위, 보 셋 중 하나 입력하세요");
		
		Thread th2 = new Thread(new RSPInput());
		th2.start();
		String scissors = "가위";
		String rock = "바위";
		String paper = "보";
		System.out.println("-------결  과--------     ");
 		System.out.println("컴퓨터 :  " + strcom);
 		System.out.println("사용자 : "  + input);		
 		if(input.equals(scissors) && strcom.equals("가위") || input.equals(paper) && strcom.equals("보") || input.equals(rock) && strcom.equals("바위")) {
 			System.out.println("서로 비겼습니다");
 		}else if(input.equals(scissors) && strcom.equals("바위") || input.equals(rock) && strcom.equals("보") || input.equals(paper) && strcom.equals("가위")) {
 			System.out.println("컴퓨터가 이겼습니다.");
 		}else  {
 			System.out.println("플레이어가 이겼습니다.");
 		}
 		System.out.println("--------------------");
	}


	private void comRandom() {
		Random ran = new Random();
		int intcom = ran.nextInt(3)+1;
		if(intcom == 1) {
			strcom = "가위";
		}else if(intcom == 2) {
			strcom = "바위";
		}else {
			strcom = "보";
		}
//		System.out.println("컴퓨터의 패 : " + strcom);
	}
}

class RSPInput implements Runnable{
	
	public static boolean inputcheck;

	@Override
	public void run() {
	inputcheck = true;
	}
} 
	
class RSPCountDown implements Runnable{
	
	@Override
	public void run() {
		for (int i = 10; i >=  1;  i--) {
			System.out.println("입력 " + i + "초 남았습니다" );
			
			if(RSPInput.inputcheck == true) {
				return;
			}
			try {
				Thread.sleep(1000); // 1초동안 잠시 멈추는 작업
			} catch (InterruptedException e) {
			}
		}
		System.out.println("시간 초과로 컴퓨터 승리!!!");
		System.exit(0);
	}

}