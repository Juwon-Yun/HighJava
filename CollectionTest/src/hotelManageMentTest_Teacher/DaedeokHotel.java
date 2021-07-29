package hotelManageMentTest_Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DaedeokHotel {
	Map<Integer, Room> hotelMap;
	Scanner sc;
	
	//생성자 - 호텔 객실 초기화
	public DaedeokHotel() {
		hotelMap = new HashMap<Integer, Room>();
		sc = new Scanner(System.in);
		
		for (int i = 2; i < 4; i++) {
			String roomType = null;
			switch(i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;
			}
			
			for (int j = 1; j <= 9; j++) {
				int roomNumber = i * 100 + j;
				hotelMap.put(roomNumber, new Room(roomNumber, roomType));
			}
		
		}
	}
	
	public static void main(String[] args) {
		new DaedeokHotel().hotelStart();
	}
	
	//시작 메서드
	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************");
	    System.out.println("   호텔문을 열었습니다. 어서오십시요.       ");
	    System.out.println("*********************************************");
	    
	    while(true) {
	    	int choice  = displayMenu();
	    	switch (choice) {
			case 1:		//체크인
				checkIn();
				break;
			case 2:		//체크아웃
				checkOut();
				break;
			case 3:		//객실상태
				displayRoomList();
				break;
			case 4:		//종료
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("   호텔문을 닫았습니다.        ");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("잘못된 입력입니다 다시입력하세요.");
				break;
			}
	    }
	}             
	//객실상태 메서드
	private void displayRoomList() {
		
		//방 번호를 순서대로 나오게 하기 위해서 방번호(Map의 Key값)만 
		//List에 넣은 후 정렬하여 사용한다.
		List<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
			
		Collections.sort(roomNumList); // 방번호의 오름차순 정렬
		
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("                   현재 객실 상태   ");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름              ");
		System.out.println("----------------------------------------------");
		
		//List에서 방번호를 하나씩 꺼내와 Map에서 해당 방번호에 대한 
		//Room객체를 구해서 출력한다.
		for (int num : roomNumList) {
			Room r = hotelMap.get(num);
			
			//투숙객 이름이 null이면 '-'로 변경해 준다.
			String name = r.getGuestName();
			if(name == null) {
				name = "-";
			}
			
			//num을 넣어주면 거기에 맞는 Room객체가 나온다
			System.out.println(r.getRoomNumber() + "\t\t" + 
								r.getRoomType() + "\t\t" +
								name);
		}
		System.out.println("----------------------------------------------");
	}
	//체크아웃 메서드
	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업                              ");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.             ");
		System.out.println("방번호 입력 >>  ");
		int num = sc.nextInt();
		
		//객실의 존재여부 검사
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		} 
		
		//해당 객실에 투숙객이 없는지 검사
		if(hotelMap.get(num).getGuestName() == null) {
			System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		//해당 객실의 현재 투숙객 이름을 구한다.
		System.out.println(num + "호 객실의 " + hotelMap.get(num).getGuestName() + "님 체크아웃을 완료하였습니다.");
		
		//체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경한다.
		hotelMap.get(num).setGuestName(null);
		
	}
	
	//체크인 메서드
	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업                                ");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸                           ");
		System.out.println(" * 301~309 : 더블룸                           ");
		System.out.println(" * 401~409 : 스위트룸                         ");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호 입력 >> ");
		int num = sc.nextInt();
		
		//객실의 존재여부 검사
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		} 
		
		//해당 객실에 다른 투숙객이 있는지 검사
		if(hotelMap.get(num).getGuestName() != null) {
			System.out.println(num + "호 객실에는 이미 다른 손님이 있습니다.");
			return;
		}
		sc.nextLine();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이	름 >>");
		String name = sc.nextLine();
		
		// 입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 저장한다.
//		**************************************************************************
//		************hotelMap.get(num); <-- Room 객체 호출*******************
//		**************************************************************************
		hotelMap.get(num).setGuestName(name);
		//Room객체의 투숙객이름을 넣음 
		
		System.out.println(num + "호 객실에 "+ name + "님이 체크인 하였습니다.");
		
	}
	
	//메뉴를 출력하고 작업번호를 입력받아서 반환하는 메서드
	private int displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?                                  ");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료     ");
		System.out.println("-----------------------------------------------------------");
		System.out.println("선택>>   ");
		int num = sc.nextInt();
		return num;
	}
}

class Room{
	private int roomNumber;
	private String roomType;
	private String guestName;
	
	
	
	public Room(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guestName == null) ? 0 : guestName.hashCode());
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (guestName == null) {
			if (other.guestName != null)
				return false;
		} else if (!guestName.equals(other.guestName))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", guestName=" + guestName + "]";
	}
	
	
}