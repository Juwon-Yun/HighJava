package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
	private IBoardService service;
	private Scanner sc;
	public BoardController() {
		service = new BoardServiceImpl();
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new BoardController().startBoard();
	}

	private void startBoard() {
		while(true) {
			displayBoard();
			int choice = displayMenu();
			switch(choice){
			case 1:
				newBoard();
				break;
			case 2:
				showBoard();
				break;
			case 3:
				searchBoard();
			case 0:
				System.out.println("게시판 프로그램 종료");
				System.exit(0);
			default:
				System.out.println("잘못된 입력입니다. 다시 입력하세요");
			}
		}
	}
	

	private void displayBoard() {
		List<BoardVO> boardList2 = service.getAllBoardList();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수               ");
		System.out.println("-------------------------------------------------------------");
		if( boardList2 == null || boardList2.size() == 0) {
			System.out.println("게시판에 작성된 글이 없습니다.");
		}else {
			for (BoardVO boardVO : boardList2) {
				System.out.println(boardVO.getBoard_no()+"   "+
										boardVO.getBoard_title()+"   "+
										boardVO.getBoard_writer()+"   "+
										boardVO.getBoard_cnt()	);
			}
		}
		System.out.println("-------------------------------------------------------------");
	
	}
	private void searchBoard() {
		sc.nextLine();
		BoardVO boardVo = new BoardVO();
		System.out.println("검색 작업");
		System.out.println("-------------------------------------------------------------");
		System.out.print("검색할 제목 입력: ");
		String title = sc.nextLine();
		if ( title.equals(" ") || title.equals(null) ) {
			displayBoard();
			return;
		}
		boardVo.setBoard_title(title);
		List<BoardVO> boardList = service.getSearchBoard(boardVo);
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수               ");
		System.out.println("-------------------------------------------------------------");
		if( boardList == null || boardList.size() == 0) {
			System.out.println("게시판에 작성된 글이 없습니다.");
		}else {
			for (BoardVO boardVO : boardList) {
				System.out.println(boardVO.getBoard_no()+"   "+
										boardVO.getBoard_title()+"   "+
										boardVO.getBoard_writer()+"   "+
										boardVO.getBoard_cnt()	);
			}
		}
		System.out.println("-------------------------------------------------------------");
	}
	private void showBoard() {
		sc.nextLine();
		BoardVO boardVo = new BoardVO();
		System.out.print("보기를 원하는 게시물 번호 입력>>");
		String num = sc.nextLine();
		boardVo.setBoard_no(num);
		//번호 전달
		service.getBoardNum(boardVo);
		service.updateCnt(boardVo);
		System.out.println(num+"번글 내용");
		
		//넘겨주는건 메소드의 매개변수, 받는건 List
		List<BoardVO> boardList = service.getBoardNum(boardVo);
		
		
		System.out.println("------------------------------------------------------------");
		System.out.println("-제   목 : " + boardList.get(0).getBoard_title()  );
		System.out.println("-작성자 : " + boardList.get(0).getBoard_writer()  );
		System.out.println("-내   용 : " + boardList.get(0).getBoard_content()  );
		System.out.println("-작성일 : " + boardList.get(0).getBoard_date()  );
		System.out.println("-조회수 : " + boardList.get(0).getBoard_cnt()  );
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴: 1.수정     2.삭제     3.리스트로가기");
		System.out.print("작업선택>>");
		int num2 = sc.nextInt();
		
		if( num2 == 1) {
			sc.nextLine();
			System.out.println("수정 작업하기");
			System.out.println("-------------------------------------------------------------");
			System.out.print("-제   목 : ");
			String title = sc.nextLine();
			System.out.print("-내   용 : ");
			String content = sc.nextLine();
			boardVo.setBoard_title(title);
			boardVo.setBoard_content(content);
			int cnt = service.updateBoard(boardVo);
			if(cnt >0) {
				System.out.println(num + "번글이 수정되었습니다");
			}else {
				System.out.println(num + "번글이 수정실패하였습니다");
			}
		}else if(num2 == 2) {
			int cnt = service.deleteBoard(boardVo);
			if(cnt >0) {
				System.out.println(num + "번글이 삭제되었습니다");
			}else {
				System.out.println(num + "번글이 삭제실패하였습니다");
			}
		}else {
			startBoard();
		}
	}
	private void newBoard() {
		sc.nextLine();
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("-제   목 : ");
		String title = sc.nextLine();
		System.out.print("-작성자 : ");
		String writer = sc.nextLine();
		System.out.print("-내   용 : ");
		String content = sc.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int count = service.insertBoard(boardVo);
		if( count > 0 ) {
			System.out.println("새글이 추가되었습니다");
		}else {
			System.out.println("새글 추가 실패");
		}
	}

	
	private int displayMenu() {
		System.out.println("메뉴: 1.새글작성     2.게시글보기     3.검색    0.작업끝");
		System.out.print("작업선택>>");
		int num = sc.nextInt();
		return num;
	}
}
