package ThreadTest;

public class Ex13_7 implements Runnable {
	static boolean autoSave = false;
	
	public static void main(String[] args) {
		Thread t  = new Thread(new Ex13_7());
		t.setDaemon(true);
		//데몬스레드를 주석처리하면 run()메소드가 무한루프로 실행된다 
		
		t.start();
		
		for (int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {			}
			System.out.println(i);
			if (i==5) autoSave = true;
		}
		System.out.println("프로그램을 종료합니다.");
	}//일반스레드


	public void run() {		//데몬스레드   (일반스레드가 하나도 없을때 자동 종료)
		while(true) {	//무한루프
			try {
				Thread.sleep(3*1000);
			} catch (InterruptedException e) {			}
			
			//autosave 값이 true 이면 autoSave()를 호출한다
			if(autoSave) {
				autoSave();
			}
		}
	}


	private void autoSave() {
		System.out.println("작업 파일이 자동 저장 되었습니다.");
	}
}