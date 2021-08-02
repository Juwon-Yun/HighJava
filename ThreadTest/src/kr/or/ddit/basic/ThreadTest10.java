package kr.or.ddit.basic;

//쓰레드의 상태를 출력하는 예제
public class ThreadTest10 {

	public static void main(String[] args) {
//		TargetThread th = new TargetThread();
//		StatePrintThread spt = new StatePrintThread(th);
//		spt.start();
		StatePrintThread  spt = new StatePrintThread(new TargetThread());
		spt.start();
	}

}

class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i = 1L; i <= 20_000_000_000L; i++) {}			//시간 지연용
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {	}	
			
			for(long j = 1L; j <= 20_000_000_000L; j++) {	}		//시간 지연용
	}
}

//TargetThread 상태를 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread  target;

	//생성자( 초기화를 위해)
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	@Override
	public void run() {
		while(true) {
			//쓰레드의 현재 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("target 쓰레드의 상태값 : " + state);
			//sleep으로 자고 있을때 : TIME-WAITING
			//for문 돌아갈때 : RUNNABLE
			//다 끝나면 TERMINATED...
			
			
			//target 쓰레드가 New상태인지 검사
			if(state == Thread.State.NEW) { //New상태이면 
				target.start();							 // 쓰레드를 실행시킨다
			}
			
			// target 쓰레드가 종료상태인지 검사
			if(state == Thread.State.TERMINATED) {		//종료 상태이면
				break;														//무한루프를 빠져나간다
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
	}
}