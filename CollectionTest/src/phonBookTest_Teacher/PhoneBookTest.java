package phonBookTest_Teacher;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner sc;
	
	public PhoneBookTest() {
		phoneBookMap = new HashMap<>();
		sc = new Scanner(System.in);
	}
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
		
	}
	private void phoneBookStart() {
		System.out.println("==============================");
		System.out.println("                        전화번호 정보 관리 프로그램");
		System.out.println("==============================");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:			//등록
				insert();
				break;
			case 2:			//수정
				update();
				break;
			case 3:			//삭제
				delete();
				break;
			case 4:			//검색
				search();
				break;
			case 5:			//전체 출력
				displayAll();
				break;
			case 0:			//프로그램 종료
				System.out.println("프로그램 종료");
//				System.exit(0);
				return;
			default :
				System.out.println("잘못된 번호를 입력했습니다");
				System.out.println("다시 입력하세요.");
				break;
			}
		}
	}

private void search() {
		sc.nextLine();
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.println("이  름 >>");
		String name = sc.nextLine();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
			System.out.println("검색 작업을 마칩니다.");
			return;
		}
		Phone p = phoneBookMap.get(name);
		System.out.println("검색한" + name + "님의 전화번호 정보.");
		System.out.println("--------------------------------------");
		System.out.println(" 		이       름 : "+ p.getName());
		System.out.println(" 		전화번호 : "+ p.getTel());
		System.out.println(" 		주       소 : "+ p.getAdd());
		System.out.println("--------------------------------------");
}
private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이  름 >> ");
		String name = sc.nextLine();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		phoneBookMap.remove(name);
		System.out.println(name + "님의 전화번호 정보를 삭제 완료했습니다.");
		
		
	}
private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.println("이 름 >>");
		String name = sc.nextLine();
	
		//해당 사람이 없으면 수정작업을 할 수없다
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.println("새로운 전화번호 >> ");
		String newTel = sc.nextLine();
		
		System.out.println("새로운 주소 >> ");
		String newAdd = sc.nextLine();

		//방법 1 ==> get( ) 메서드로 value 값을 구해서 처리하기
		Phone p = phoneBookMap.get(name);
		p.setTel(newTel);
		p.setAdd(newAdd);
		
		// 방법2 => 같은 key 값에 새로운 전화번호 정보를 추가한다.
		// 			  => 나중의 데이터로 변경된다.
//		phoneBookMap.put(name, new Phone(name, newAdd, newTel));
//		
		System.out.println(name + "님의 전화번호 정보를 변경했습니다.");
}
private void displayAll() {
		System.out.println();
		
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("------------------------------------------");
		System.out.println(" 번호		이름			전화번호 		주소 ");
		System.out.println("------------------------------------------");
		
		if(phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			int count = 0;  //번호 출력용
			for (String show : phoneKeySet) {
				count++;
				Phone p = phoneBookMap.get(show);
				System.out.println(" " + count + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAdd());
			}
		}
		System.out.println("-----------------------------------------");
		System.out.println("출력 끝...");
			
}
// 새로운 전화번호 정보를 등록하는  메소드
private void insert() {
	sc.nextLine();
	System.out.println();
	System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
	System.out.println("이 름>>");
	String name = sc.nextLine();
	
	//이미 등록된 사람인지 검사
	if(phoneBookMap.containsKey(name)) {
		System.out.println(name + "님은 이미 등록된 사람입니다.");
		return;
	}
	
	System.out.println("전화번호 >> ");
	String tel = sc.next();
	System.out.println(" 주 소 >> ");
	String addr = sc.next();
	
	//입력받은 데이터를 Phone객체에 저장한 후 Map에 추가한다.
	
//	Phone P = new Phone(name, addr, tel);
//	phoneBookMap.put(name , P);

	phoneBookMap.put(name ,new Phone(name, addr, tel));
	System.out.println(name + " 님의 전화번호 등록 완료!!!");
}	
private int displayMenu() {
	System.out.println();
	System.out.println("1. 전화번호 등록");
	System.out.println("2. 전화번호 수정");
	System.out.println("3. 전화번호 삭제");
	System.out.println("4. 전화번호 검색");
	System.out.println("5. 전화번호 전체 출력");
	System.out.println("0. 프로그램 종료");
	System.out.println("-------------------------------------------------");
	System.out.println("번호입력 >> ");
	int num = sc.nextInt();
	return num;
}

class Phone{
	private String name, tel,add;

	
	public Phone(String name, String tel, String add) {
		super();
		this.name = name;
		this.tel = tel;
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", add=" + add + "]";
	}
	
	
	}
}