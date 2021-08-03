package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제
/*
 	원주율 계산하는 쓰레드와
 	계산된 원주율을 출력하는 쓰레드가 있다.
 	
 	volatile 원주율 관리  => 계산하는쓰레드, 출력하는쓰레드
 	
 	원주율을 저장하는 객체가 필요하다.
 	이 객체를 두 쓰레드가 공통으로 사용해서 처리한다.
 	
 */
public class ThreadTest15 {

	public static void main(String[] args) {
		//공통으로 사용할 객체 인스턴스 생성
		ShareData sd = new ShareData(); 
		
		//처리할 쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPIThread cpt = new CalcPIThread();
		cpt.setSd(sd);
		
		PrintPIThread ppt = new PrintPIThread(sd);
		
		cpt.start();
		ppt.start();
		
	}
}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;
	// 공통으로 사용할 객체를 초기화 한다.
	// 방법 1) setter를 이용해서 초기화 하기
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		if(sd == null) {
			System.out.println("원주율 계산 오류...");
			return;
		}
		/*
		 	PI => 3.141592...
		 	원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ...) * 4;
		 					+1	 -  3	 +  5	  -  7   +  9... => 분모
		 					  0     1       2      3        4... => 2로 나눈 몫
		 */
		double sum = 0.0;
		for(int i = 1; i <= 1000000000; i+=2) {
			//짝수
			if( ((i/2) %2) == 0 ) {
				sum += (1.0 / i);
			//홀수 
			}else {
				sum -= (1.0 / i);
			}
		}
		// 계산된 원주율을 공통 객체의 멤버변수에 저장
		sd.result = sum * 4;
		// 계산이 완료되었음을 나타냄
		sd.isOk = true;
	}
	
	
}

// 계단이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;
	// 공통으로 사용할 객체를 초기화 한다.
	// 방법 2) 생성자를 이용해서 초기화 하기
	public PrintPIThread(ShareData sd) {
		super();
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			// 원주율 계산이 완료 될 때까지 기다린다.
			if(sd.isOk == true) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("		P I   : " + Math.PI);
		
	}
	
}

// 원주율 관리하는 클래스 (공통으로 사용할 클래스)

class ShareData {
	//계산된 원주율이 저장될 변수
	public double result; 
	
	// volatile => CPU의 각 코어에 캐시가 있는데 이 캐시를 사용하지 않고 직접 
	//					  메모리에서 데이터값을 입출력한다.						 
	//선언된 변수를 컴파일러의 최적화 대상에서 제외 시킨다.
	//즉, 값이 변경되는 즉시 변수에 적용시킨다.
	//다중 쓰레드에서 하나의 변수가 완벽하게 한번에 작동되도록 보장하는 키워드(일종의 동기화)
	//추가설명) 
	//			- 하나의 Thread가 아닌 여러 Thread가 write 하는 상황에서는 적합하지 않다.
	// 			- 여러 Thread가 write하는 상황이라면?
	//					= synchronized를 통해 변수 read & write의 원자성(atomic)을 보장해야함
	
	
	//계산이 완료되었는지를 나타내는 변수( 완료되면 true가 됨)
	public volatile boolean isOk = false;
	
}