package lottoexercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
public class LottoTest {
	Scanner sc = new Scanner(System.in);
	List<Integer> lottoNum;
	int money;
	int temp;
	int count = 1;
	public static void main(String[] args) {
		new LottoTest().start();
	}
		View view = View.getInstance();

		public int start() {
			view.homeView();
					int input = sc.nextInt();
				switch (input) {
				case 1:
					lottoShop();
					break;
				case 2:
					System.out.println("프로그램을 종료합니다 감사합니다");
					System.exit(0);
				default:
					System.out.println("잘못된 입력입니다 1이나 2를 입력하세요");
					start();
				}
				sc.close();
				return View.HOME;
		}			
	public void lottoShop() {
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액입력 => ");
		money = sc.nextInt();
		temp = money / 1000;
		if( temp > 100) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			start();
		}else if(money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			start();
		}else {
			System.out.println();
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			for (int i = 0; i < temp; i++) {
				ranNum();
				count++;
			}
			System.out.println();
			System.out.println("받은 금액은 "+money+"원이고 거스름돈은 " + money%1000+"입니다.");
		}
	}
	private void ranNum() {
		Set<Integer> ranSet = new HashSet<>();
		Random ran = new Random();
		while(ranSet.size() < 6) {
			ranSet.add(ran.nextInt(45) + 1);
		}
		lottoNum = new ArrayList<>(ranSet);
		
		Collections.shuffle(lottoNum);
		System.out.println("로또 번호" + count + ": " + lottoNum);
	}
}
