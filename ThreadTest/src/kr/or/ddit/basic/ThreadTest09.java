package kr.or.ddit.basic;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
  	컴퓨터와 가위 바위 보를 진행하는 프로그램 작성하기
  	
  	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
  	사용자의 입력은 showInputDialog( )메소드를 이용해서 입력 받는다.
  	
  	입력 시간은 5초로 제한하고 카운트 다운을 한다.
 	5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 
 	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 	
 	결과예시)
 	
 	-------결  과--------
 		컴퓨터 : 가위
 		사용자 : 바위
 		결   과 :  플레이어가 이겼습니다.
 		
 	5초안에 입력을 못했을 경우
 	-------결  과--------
 		시간 초과로 플레이어 패배.
 */
public class ThreadTest09 {
	Scanner sc = new Scanner(System.in);
	String strcom;
	public static String input;
	
	public static void main(String[] args) {
		new ThreadTest09().start();
	}
	
	public void start() {
		System.out.println("------가위바위보머신--------");
		System.out.println("1. 게임시작  2.게임 종료");
		System.out.println("------------------------------");
		System.out.print("번호 입력 >>  ");
		
		while(true) {
			int input = sc.nextInt();
			switch (input) {
			case 1:
				gameStart();
				break;
			case 2:
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	private void gameStart() {
		comRandom();
		input = JOptionPane.showInputDialog("가위, 바위, 보 셋 중 하나 입력하세요");
		Thread th1 = new Thread(new RSPCountDown2());
		Thread th2 = new Thread(new RSPInput2());
		
		th1.start();
		th2.start();

		String scissors = "가위";
		String rock = "바위";
		String paper = "보";
		
		showresult();
		if(input.equals(scissors) && strcom.equals("가위") || input.equals(paper) && strcom.equals("보") || input.equals(rock) && strcom.equals("바위")) {
			System.out.println("서로 비겼습니다");
		}else if(input.equals(scissors) && strcom.equals("바위") || input.equals(rock) && strcom.equals("보") || input.equals(paper) && strcom.equals("가위")) {
			System.out.println("컴퓨터가 이겼습니다.");
		}else  {
			System.out.println("플레이어가 이겼습니다.");
		}
		
	}
	private void showresult() {
		System.out.println("-------결  과--------     ");
 		System.out.println("컴퓨터 :  " + strcom);
 		System.out.println("사용자 : "  + input);
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
		System.out.println("컴퓨터의 패 : " + strcom);
	}

	
}
class RSPInput2 implements Runnable{
	
	public static boolean inputcheck;

	@Override
	public void run() {

//	String str =ThreadTest09.input;
	inputcheck = true;
	}
} 
	
class RSPCountDown2 implements Runnable{
	
	@Override
	public void run() {
		for (int i = 10; i >=  1;  i--) {
			System.out.println("입력" + i + "초 남았습니다" );
			
			if(RSPInput.inputcheck == true) {
				System.out.println("잘입력하셧네요 근데 결과는 ???");
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


	


