package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class ThreadTest_Teacher {
	public static boolean inputcheck;
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		Random rnd = new Random();
		
		//난수를 이용해서 컴퓨터에 가위바위보를 정함
		String [ ] data = {"가위", "바위", "보"};
		int index = rnd.nextInt(3);  // 0~2사이의 난수생성
		String com = data[index];

		//사용자의 가위바위보 입력받기
		gt.start();
		String man = null;
		
		do {
		man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
		}while(!(man.equals("가위") || man.equals("바위") || man.equals("보")));
		//do-while 문을 써서 가위 바위 보가 아닐때 무한 반복하게함
		
		inputcheck = true;
		
		
		//결과 판정하기
		String result = "";		
		if(com.equals(man)) {
			result = "비겼습니다";
		}else if(man.equals("가위") && com.equals("보") ||
				man.equals("바위") && com.equals("가위") ||
				man.equals("보") && com.equals("바위")) {
			result = "사용자가 이겼습니다.";
		}else {
			result = "컴퓨터가 이겼습니다.";
		}
		
		System.out.println(" -- 결과 -- ");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 사용자 : " + man);
		System.out.println(" 결과 : " +result);
	
	}

}










class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for (int i = 5; i >= 1; i--) {
			if(ThreadTest_Teacher.inputcheck == true)
				return;
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {			}
		
		}
		System.out.println("시간초과로 당신이 졌습니다.");
		System.exit(0);
	}
}