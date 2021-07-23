package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1. 네이버");
		b.history();
		
		b.goURL("2. 구글");
		b.history();
		
		b.goURL("3. 다음");
		b.history();

		b.goURL("4. 네이트");
		b.history();
		
		System.out.println("뒤로가기 후..");
		b.goBack();
		b.history();

		System.out.println("뒤로가기 후..");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기 후..");
		b.goForword();
		b.history();
		
		System.out.println("새로운 사이트 접속 후..");
		b.goURL("5. 대덕IT");
		b.history();
	}

}

//웹브라우저의 앞으로가기, 뒤로가기
class Browser{
	private LinkedList<String> back;	//이전 방문 내역이 저장될 스택
	private LinkedList<String> forward;	//다음 방문 내역이 저장될 스택
	private String currentURL;
	// ArrayList => 내부구조가 배열을 이용해서 데이터관리
	// LinkedList => Linked라는 자료구조를 이용해서 데이터관리
	public Browser() {
		back = new LinkedList<String>();
		forward = new LinkedList<String>();
		currentURL ="";
	}
	//사이트를 방문하는 메서드 => 매개변수에는 방문할 URL주소가 저장된다.
	public void goURL(String url) {
		System.out.println(url+ " 사이트에 접속합니다.");
		if(currentURL != null && !"".equals(currentURL)) {
			back.push(currentURL);
		}
		currentURL = url;
		forward.clear();
	}
	public void goBack() {
			// .isEmpty( ) => List가 비어 있으면 true, 그렇지 않으면 false 반환
		if(!back.isEmpty()) {  
			forward.push(currentURL);
			currentURL = back.pop();
		}
	}
	
	public void goForword() {
		// .isEmpty( ) => List가 비어 있으면 true, 그렇지 않으면 false 반환
		if(!forward.isEmpty()) {  
			back.push(currentURL);
			currentURL = forward.pop();
		}
	}
	
	public void history() {
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("	   방 문 기 록          ");
		System.out.println("--------------------------");
		System.out.println("back          =>  " + back);
		System.out.println("현재          =>  " + currentURL);
		System.out.println("forward     =>  " + forward);
		System.out.println();
	}
}







