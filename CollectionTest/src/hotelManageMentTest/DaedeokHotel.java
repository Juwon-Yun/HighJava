package hotelManageMentTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DaedeokHotel {
	private Map<Integer, Room> hotelMap = new HashMap<>();
	private Scanner sc = new Scanner(System.in);
	View view = new View();
	
	public static void main(String[] args) {
		new DaedeokHotel().start();
	}
	
	
	private void start() {
		view.openView();
		while(true) {
			view.selectView();
			int input = sc.nextInt();
			switch (input) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				checkRoom();
				break;
			case 4:
				view.endManageMentView();
				System.exit(0);
			default:
				System.out.println("잘못된 입력입니다 다시 입력해주세요");
				break;
			}
		}
	}
//	private void addroomlist() {
//		for (int i = 201; i < 210; i++) {
//			hotelMap.put(i, new Room(i,"싱글룸","-"));
//		}
//		for (int i = 301; i < 310; i++) {
//			hotelMap.put(i, new Room(i,"더블룸","-"));
//		}
//		for (int i = 401; i < 410; i++) {
//			hotelMap.put(i, new Room(i,"스위트룸","-"));
//		}
//		
//	}
	private void checkIn() {
		sc.nextLine();
		int num=0;
		String name;
		view.checkInView();
		num = sc.nextInt();
		if(hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 이미 손님이 있습니다.");
			return;
		}

		sc.nextLine();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름입력 >>");
		name = sc.nextLine();
		

		if(!(num >= 201 && num <= 209 || num >= 301 && num <= 309 || num >= 400 && num <= 409)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		if(num >= 201 && num <= 209 ){
			hotelMap.put(num, new Room(num,"싱글룸", name));
		}else if(num >= 301 && num <= 309 ) {
			hotelMap.put(num, new Room(num,"더블룸", name));
		}else {
			hotelMap.put(num, new Room(num,"스위트룸", name));
		}
		
		System.out.println("체크인이 완료되었습니다");
	}

	private void checkOut() {
		view.checkOutView();
		int num = sc.nextInt();
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다");
			return;
		}
		
		if(!(num >= 201 && num <= 209 || num >= 301 && num <= 309 || num >= 400 && num <= 409)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		System.out.println(num + "호 객실의 " +hotelMap.get(num).getRentPerson() +"님 체크아웃을 완료하였습니다.");

		if(num >= 201 && num <= 209 ){
			hotelMap.remove(num);
		}else if(num >= 301 && num <= 309 ) {
			hotelMap.remove(num);
		}else if(num >=401 && num <= 409){
			hotelMap.remove(num);
		}else {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
		}
		
		
	}

	private void checkRoom() {
		view.checkInStateView();
		Set<Integer> hotelKeySet = hotelMap.keySet();
//		int num=0;
//		if(hotelMap.containsKey(num)) {
//			
//		}
		for (Integer show : hotelKeySet) {
			Room r = hotelMap.get(show);
			System.out.println(r.getRoomNum() + "\t\t\t" + r.getRoomKind() + "\t\t\t" + r.getRentPerson());
		}
		System.out.println("-----------------------------------------------------------");
	}


}
