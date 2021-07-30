package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//Thread가 수행되는 시간 체크해 보기
		Thread th = new Thread(new SumRunner());
		
		//1970년 1월1일0시0분0초 (표준시간)부터 현재까지 경과한 시간을
		//밀리세컨드(1/1000초)단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join();
			//현재 실행중인 쓰레드에서 대상이 되는 쓰레드(변수 th)가
			//종료될 때까지 기다린다.
			//결론: join()을 이용해서 걸린 시간을 구할 수 있다.
		} catch (InterruptedException e) {
		}

		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime - startTime));
		System.out.println("메인 메소드 퇴근");
	}
}

class SumRunner implements Runnable {
	
	@Override
	public void run() {
		long sum = 0;
		for (long i = 1L; i < 1000000000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " +sum);
	}
}