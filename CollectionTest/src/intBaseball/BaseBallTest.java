package intBaseball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTest {
	List<Integer> numList;
	List<Integer> userList;
	int strike, ball;
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new BaseBallTest().startGame();
	}
	
	//게임이 시작되는 메서드
	public void startGame() {
		//난수를 만드는 메서드 호출
		getNum();
		
//		//확인용 출력
//		System.out.println("컴퓨터 난수 : " + numList);
		
		int count = 0;
		
		while(true) {
			count++;
			//사용자 입력메소드 호출
			inputNum();
			//볼카운트를 구하는 메소드 호출
			ballcount();
			if(strike ==3) {
				System.out.println("축하합니다.");
				System.out.println("당신은 "+count+ "번째만에 맞췄습니다.");
				break;	
			}
		}
		// 답을 모두 맞춘 후 결과 출력하기
		
	}
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	// Set이용
	public void getNum() {
		Set<Integer> numSet = new HashSet<>();
		Random ran = new Random();
		// 1~9사이의 난수 3개 만들기
		while(numSet.size() < 3) {
			numSet.add(ran.nextInt(9)+1);
		}
		//만들어진 난수를 List에 저장하기
		numList = new ArrayList<Integer>(numSet);
		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);
	}
	// 사용자로부터 3개의 정수를 입력받아서  List에 저장하는 메서드
	// 입력한 정수는 중복되지 않는다.
	public void inputNum() {
		int n1, n2, n3;	 //입력한 정수가 저장될 변수 선언
		
		do {
			System.out.print("숫자입력 =>");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();
			if(n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (n1 == n2 || n1 == n3 || n2 == n3);
		//입력받은 데이터를 List에 저장한다
		userList = new ArrayList<>();
		
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	//스크라이트와 볼을 판정하고 그 결과를 출력하는 메서드
	public void ballcount() {
		strike = 0;
		ball = 0;
		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
					//값이 같은지 비교
				if(numList.get(i) == userList.get(j)) {
					//위치가 같은지 비교
					if(i==j) {
						strike++;
					}else {
						ball++;
					}
				}
			} //for문 j
		}//for문 i
		//볼카운트 결과를 출력한다.
		System.out.println(userList.get(0) + "  " + userList.get(1) + "  " + userList.get(2) + 
				"==> "+ strike + "S " + ball + "B");
	}
	
}
