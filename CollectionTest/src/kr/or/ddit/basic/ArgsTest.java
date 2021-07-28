package kr.or.ddit.basic;

public class ArgsTest {

	
	//배열을 이용하는 메서드
	public int sumArr(int [] data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	// 가변형 인수 => 메서드의 인수값이 실행될 때마다 다를 때 사용한다.
	//					- 가변형 인수는 메서드안에서는 배열로 처리된다.
	//					- 가변형 인수는 한가지 자료형만 사용할 수 있다.
	
	//가변형 인수를 사용한 메서드 
	public int sumArg(int ...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형인수를 제일 뒤쪽에 배치해야한다. 
	public String sumArg2(String name, int ...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + " 님의 총점:  " + sum;
	}
	
	
	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();
		int [] nums = { 100, 200, 300};
		int [] nums2;
		nums2 = new int [] {100, 200, 300,400,500};
		at.sumArr(nums);
		at.sumArr(nums2);
		at.sumArr(new int [] {100, 200, 300, 400, 500});
		
		at.sumArg(100, 200, 300, 400, 500);
		
		at.sumArg2("윤주원",100, 200, 300);
	}

	
}
