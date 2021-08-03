package kr.or.ddit.basic;

public class ThreadTest15_runnable {

	public static void main(String[] args) {
		ThreadTest15_runnable_SD sd =  new ThreadTest15_runnable_SD();
		
		ThreadTest15_runnable_CalcPI e = new ThreadTest15_runnable_CalcPI();
		e.setSd(sd);
		// Thread cpt = new Thread(new ThreadTest15_runnable_CalcPI());
		// 를 사용하면  객체의 참조값을 모르기 때문에 cpt.setSd();를 사용 못한다.
		Thread cpt = new Thread(e);
		
		Thread ppt = new Thread(new ThreadTest15_runnable_PrintPI(sd));
		
		cpt.start();
		ppt.start();
	
	}
	
}

class ThreadTest15_runnable_CalcPI implements Runnable{
	private ThreadTest15_runnable_SD sd;
	
	
	public void setSd(ThreadTest15_runnable_SD sd) {
		this.sd = sd;
	}

	
	@Override
	public void run() {
		if(sd == null) {
			System.out.println("원주율 계산 오류...");
			return;
		}
		double sum = 0.0;
		for(int i = 1; i <= 1000000000; i+=2) {
			if( ((i/2) %2) == 0 ) {
				sum += (1.0 / i);
			}else {
				sum -= (1.0 / i);
			}
		}
		sd.result = sum * 4;
		sd.isOk = true;
	}
}
class ThreadTest15_runnable_PrintPI implements Runnable{
	private ThreadTest15_runnable_SD sd;
	
	public ThreadTest15_runnable_PrintPI(ThreadTest15_runnable_SD sd) {
		super();
		this.sd = sd;
	}

	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) {
				break;
			}		
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("		P I   : " + Math.PI);
	}
}

class ThreadTest15_runnable_SD{
	public double result;
	public volatile boolean isOk = false;
}