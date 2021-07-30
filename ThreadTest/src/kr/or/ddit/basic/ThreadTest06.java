package kr.or.ddit.basic;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread autoSaveth = new AutoSaveThread();
		
		// 데몬스레드로 설정하기
		// start( )메소드 호출 전에 설정해야 한다.
		// 설정 없으면 default : 일반 스레드
		autoSaveth.setDaemon(true);
		autoSaveth.start();
		
		for (int i = 1; i <= 20;i++) {
			System.out.println(i + "번째 어떤 작업 ");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("메인 쓰레드 종료");
	}

}

//자동 저장하는 쓰레드 ( 3초에 한번씩 자동 저장하는 쓰레드)
class AutoSaveThread extends Thread{
	
	//	작업 내용을 저장하는 메소드
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			save(); // 저장기능 호출
		}
	}
}