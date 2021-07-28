package hotelManageMentTest;

public class View {
	
	
	public void openView() {
		System.out.println("*********************************************");
		System.out.println("		호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();
	}
	public void selectView() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?"); 
		System.out.println("1.체크인		2.체크아웃		3.객실상태		4.업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 >> ");
	}
	public void checkInView() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("  		    체크인 작업");
		System.out.println("-----------------------------------------------------------");
		System.out.println(" * 201 ~ 209  : 싱글룸");
		System.out.println(" * 301 ~ 309  : 더블룸");
		System.out.println(" * 401 ~ 409  : 스위트룸");
		System.out.println("-----------------------------------------------------------");
		System.out.print("방 번호 입력 >> ");
	}
	public void checkOutView() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("  		    체크아웃 작업");
		System.out.println("-----------------------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
	}
	public void checkInStateView() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("  		    현재 객실 상태");
		System.out.println("-----------------------------------------------------------");
		System.out.println("방 번호\t\t방 종류\t\t투숙객 이름");
		System.out.println("-----------------------------------------------------------");
	}
	public void endManageMentView() {
		System.out.println("*********************************************");
		System.out.println("  		    호텔문을 닫았습니다.");
		System.out.println("*********************************************");
	}
}
