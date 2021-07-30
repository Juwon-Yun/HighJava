package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07 {

	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		// => 입력값은 문자열로 처리된다.
		// => 입력창에서 '취소' 버튼을 누르면 null값이 저장된다.
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
//		while (true) {
			if (str == null) {
				str = JOptionPane.showInputDialog("어떤 값이든 입력하세요 좀");
			}else if(str != null) {
				System.out.println("잘 입력하셧네요");
//				break;
//			}
		}
		System.out.println("입력한 값: "+str);
		
		for (int i = 10; i >=  1;  i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초동안 잠시 멈추는 작업
			} catch (InterruptedException e) {
			}
		}
		
	}
}
