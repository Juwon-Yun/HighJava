package phonBookTest;

public class View {
	static int HOME = 0;
	
	private static View instance;
	public static View getInstance() {
		if(instance == null) {
			instance = new View();
		}
		return instance;
	}
	
	public int homeView() {
		System.out.println("--------------------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------------------");
		System.out.print("번호입력 >> ");
		return 0;
	}
}
