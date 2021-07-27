package phonBookTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
 			Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 			(Map의 구조는 key값으로 '이름'을 사용하고
 									value값으로는 'Phone클래스의 인스턴스'로 한다.)
 									
 			아래 메뉴 및 예시에 맞는 기능을 구현하시오.
 			(삭제, 검색은 '이름'을 입력받아서 처리한다)
 			
 			1.전화번호 등록
 			2.전화번호 수정
 			3.전화번호 삭제
 			4.전화번호 검색
 			5.전화번호 전체 출력
 			0.프로그램 종료
 -------------------------------------
 번호입력 >> 1
 
 새롭게 등록할 전화번호 정보를 입력하세요.
 이름 >> 홍길동
 전화번호 >> 010-1234-5678
 주 소 >> 대전시 중구 대흥동
 
 '홍길동' 전화번호 등록 완료!!
 
 			1.전화번호 등록
 			2.전화번호 수정
 			3.전화번호 삭제
 			4.전화번호 검색
 			5.전화번호 전체 출력
 			0.프로그램 종료
 -------------------------------------
 번호입력 >> 1

 새롭게 등록할 전화번호 정보를 입력하세요.
  이름 >> 홍길동
  
 '홍길동'은 이미 등록된 사람입니다.

  			1.전화번호 등록
 			2.전화번호 수정
 			3.전화번호 삭제
 			4.전화번호 검색
 			5.전화번호 전체 출력
 			0.프로그램 종료
 -------------------------------------
 번호입력 >> 5


------------------------------------------------
번호			이름				전화번호				주소
------------------------------------------------
  1				홍길동		010-1234-5678	 대전시 중구 대흥동
 ~~
 ~~
 ------------------------------------------------
 출력 완료...
 
 			1.전화번호 등록
 			2.전화번호 수정
 			3.전화번호 삭제
 			4.전화번호 검색
 			5.전화번호 전체 출력
 			0.프로그램 종료
 -------------------------------------
 번호입력 >> 0
 
 프로그램을 종료합니다.
 */
public class PhoneBookTest {
	Scanner sc  = new Scanner(System.in);
	Map<String, Member> map = new HashMap<>();
	Member mem = new Member();
	String inputName, inputTel, inputAdd;
	
	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	View view = View.getInstance();

	public int start() {
		while(true) {
			View.HOME = view.homeView();
			int input = sc.nextInt();
			switch (input) {
			case 1:
				addTel();
				break;
			case 2:
				updateTel();
				break;
			case 3:
				delTel();
				break;
			case 4:
				searchTel();
				break;
			case 5:
				showTelList();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못된 입력입니다 다시 입력해주세요.");
				start();
			}
		}
	}

	private int addTel() {
		sc.nextLine();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		inputName = sc.nextLine();
		mem.setName(inputName);
		
		System.out.print("전화번호 >> ");
		inputTel = sc.nextLine();
		mem.setTel(inputTel);

		System.out.print("주소 >> ");
		inputAdd = sc.nextLine();
		mem.setAdd(inputAdd);
		
		map.put(inputName, mem);
		
		System.out.println("'"+inputName+"' 전화번호 등록 완료!!!");
		return start();
	}

	private int updateTel() {
		sc.nextLine();
		System.out.println("변경할 회원의 이름 입력하세요.");
		System.out.print("이름 >> ");
		inputName = sc.nextLine();
		if(map.containsKey(inputName)) {
			System.out.println("변경할 전화번호를 입력하세요.");
			System.out.print("전화번호 >> ");
			inputTel = sc.nextLine();
			mem.setTel(inputTel);
			map.put(inputName, mem);
			System.out.println("'"+inputName+"' 회원님의 전화번호 변경 완료!!!");
		}else{
			System.out.println("등록되어있지 않은 회원정보입니다.");
		}
		return start();
	}


	private int delTel() {
		sc.nextLine();

		System.out.println("삭제할 회원의 이름 입력하세요.");
		inputName = sc.nextLine();
		
		if(map.containsKey(inputName)) {
			System.out.println("전화번호 삭제가 완료되었습니다.");
			map.remove(inputName, mem);
			System.out.println("'"+inputName+"' 회원님의 전화번호 삭제 완료!!!");
		}else{
			System.out.println("등록되어있지 않은 회원정보입니다.");
		}
		
		return start();
	}

	private int searchTel() {
		sc.nextLine();
		
		System.out.println("조회할 회원의 이름 입력하세요.");
		inputName = sc.nextLine();
		
		if(map.containsKey(inputName)) {
			System.out.println("해당 회원의 전화번호 정보입니다.");
			Member mem = map.get(inputName);
			System.out.println(mem.getName());
			System.out.println(mem.getTel());
			System.out.println(mem.getAdd());
		}else{
			System.out.println("등록되어있지 않은 회원정보입니다.");
		}
		
		return start();
	}

	private int showTelList() {
		sc.nextLine();
		
		System.out.println("조회할 회원의 이름 입력하세요.");
		inputName = sc.nextLine();
		
		if(  map.containsKey(inputName)  ) {
			System.out.println("해당 회원의 전화번호 정보입니다.");
			for (Member show : map.values()) {
				System.out.println(show);
			}
		}else{
			System.out.println("등록되어있지 않은 회원정보입니다.");
		}
		
		return start();
	}
}
