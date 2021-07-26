package lottoexercise;

public class View {
	static final int HOME = 0;
	static final int MAIN = 1;
	
	private static View instance;
	public static View getInstance() {
		if(instance == null) {
			instance = new View();
		}
		return instance;
	}
	
	public int homeView() {
		System.out.println("=======================");
		System.out.println(" Lotto 프로그램");
		System.out.println(" -----------------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("=======================");
		System.out.print("메뉴선택: ");
		return 0;
	}
}
