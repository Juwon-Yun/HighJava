package kr.or.ddit.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	//Service 객체 변수 선언
	private IMemberService service;
	private Scanner sc;
	//생성자
	public MemberController() {
		//Service 객체 생성
		service = MemberServiceImpl.getInstance();
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) throws SQLException {
		//여기가 Service객체 만들어지는시점 
		new MemberController().startMember();
	}
	public void startMember() {			
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1:	// 자료추가
					insert();
					break;
				case 2:	// 자료삭제
					delete();
					break;
				case 3:	// 자료수정
					update();
					break;
				case 4:	//전체 자료 출력
					displayMember();
					break;
				case 5:	//선택 자료 수정
					update2();
					break;
				case 0:
					System.out.println("작업을 마칩니다.");
					System.exit(0);
				default : System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	//Controller에서는 DB관련 코드가 보이면 안된다
		private void insert() {
			sc.nextLine();
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			int count = 0;
			String memId = null;
			
			//아이디가 중복될때 다시입력받는 부분
			do {
			System.out.print("회원 아이디 >>");
			memId = sc.nextLine();
			count = service.getMemberCount(memId);
			if( count > 0) {
				System.out.println(memId + "는(은) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
			}while(count > 0);
			
			System.out.print("비밀번호 >> ");
			String memPass = sc.nextLine();
			System.out.print("회원이름 >> ");
			String memName = sc.nextLine();
			System.out.print("전화번호 >> ");
			String memTel = sc.nextLine();
			System.out.print("회원주소 >> ");
			String memAddr = sc.nextLine();
			
			//입력한 데이터들을 MemberVO객체에 담는다.
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memId);
			memVo.setMem_pass(memPass);
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			int cnt = service.insertMember(memVo);

			if(cnt > 0 ) {
				System.out.println("추가 작업 성공");
			}else {
				System.out.println("추가 작업 실패");
			}
		}
		
		private void delete() {
			System.out.println();
			System.out.println("삭제할 회원 정보를 입력하세요.");
			System.out.print("삭제할 회원ID >> ");
			String memId = sc.next();
			
			int cnt = service.deleteMember(memId);
			
			if(cnt > 0) {
				System.out.println("삭제 작업 성공");
			}else {
				System.out.println("삭제 작업 실패");
			}
		}
		private void update() {
			sc.nextLine();
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("수정할 회원아이디 >>");
			String memId = sc.nextLine();
			
			int count = service.getMemberCount(memId);
			
			// 없는 회원
			if ( count == 0 ) {
				System.out.println(memId + "은(는) 없는 회원ID 입니다.");
				System.out.println("수정 작업을 종료합니다.");
				return;
			}
			
			System.out.println("수정할 내용을 입력하세요.");
			System.out.print("새로운 비밀번호 >>");
			String newPass = sc.nextLine();
			
			System.out.print("새로운 회원이름 >>");
			String newName = sc.nextLine();
			
			System.out.print("새로운 전화번호 >>");
			String newTel = sc.nextLine();
			
			System.out.print("새로운 회원주소 >>");
			String newAddr = sc.nextLine();
			
			//입력한 데이터들을 MemberVO객체에 저장한다
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memId);
			memVo.setMem_pass(newPass);
			memVo.setMem_name(newName);
			memVo.setMem_tel(newTel);
			memVo.setMem_addr(newAddr);
			
			int cnt = service.updateMember(memVo);
			if(cnt >0) {
				System.out.println("수정 작업 성공");
			}else {
				System.out.println("수정 작업 실패");
			}
		}
		private void update2() {
			System.out.println();
			sc.nextLine();
			System.out.println("수정할 회원정보를 입력하세요");
			System.out.print("수정할 회원 ID >>");
			String memId = sc.nextLine();
			
			int count = service.getMemberCount(memId);
			if( count ==0 ) {
				System.out.println(memId + "은(는) 없는 회원ID 입니다");
				System.out.println("수정 작업을 종료합니다.");
				return;
			}
			
			int num;
			// 선택한 항목의 컬럼명이 저장될 변수
			String updateField = null;
			// 선택한 항목의 값을 입력받을 때 나타낼 메세지가 저장될 변수
			String updateTitle = null;
			
			do {
				System.out.println();
				System.out.println("수정할 항목을 선택하세요");
				System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
				System.out.println("--------------------------------------------------------");
				System.out.print("수정 항목 선택 >> ");
				num = sc.nextInt();
				
				switch (num) {
				case 1: updateField = "mem_pass"; updateTitle="비밀번호";				
					break;
				case 2: updateField = "mem_name"; updateTitle="회원이름";				
					break;
				case 3: updateField = "mem_tel"; updateTitle="전화번호";				
					break;
				case 4: updateField = "mem_addr"; updateTitle="회원주소";				
					break;
				default:
					System.out.println("수정할 항목을 잘못 선택했습니다");
					System.out.println("다시 선택하세요");
				}
			} while (num < 1 || num > 4);
				System.out.println();
				//버퍼지우기
				sc.nextLine();
				// 수정할 값 입력받기
				System.out.println("새로운" + updateTitle + ">>");
				String updateData = sc.nextLine();
				
				// 수정 작업에 필요한 데이터들을 Map에 저장한다.
				Map<String, String> paramMap = new HashMap<>();
				// 회원ID 추가
				paramMap.put("memId", memId);
				// 수정할 컬럼명 추가
				paramMap.put("field", updateField);
				// 수정할 데이터 추가
				paramMap.put("data", updateData);
				
				int cnt = service.updateMember2(paramMap);
				
				if(cnt > 0 ) {
					System.out.println("수정 작업 성공");
				}else {
					System.out.println("수정 작업 실패");
				}
		}
		private void displayMember() {
			List<MemberVO> memList = service.getAllMemberList();
			
			System.out.println("---------------------------------------------------------------------");
			System.out.println("ID	비밀번호		이름		전화번호			주소");
			System.out.println("---------------------------------------------------------------------");
			
			if(memList == null || memList.size() == 0) {
				System.out.println("회원 자료가 하나도 없습니다");
			}else {
				for(MemberVO memVo : memList) {
					System.out.println(
							memVo.getMem_id() + "   "+
							memVo.getMem_pass() + "   "+
							memVo.getMem_name() + "   "+
							memVo.getMem_tel() + "   "+
							memVo.getMem_addr());
					
				}
			}
			System.out.println("---------------------------------------------------------------------");
			System.out.println("			출력 끝");
				
		}
	private int displayMenu() {
		System.out.println("===================");
	 	System.out.println("	-- 작업 선택 --");
	 	System.out.println("	1. 자료 추가  ");
		System.out.println("	2. 자료 삭제  ");
		System.out.println("	3. 자료 수정  ");
		System.out.println("	4. 전체 자료 출력");
		System.out.println("	5. 선택 자료 수정  ");
		System.out.println("	0. 작업 끝.   ");
	 	System.out.println("===================");
	 	System.out.print("원하는 작업 선택 >> ");
	 	int num = sc.nextInt();
	 	return num;
	}
}
