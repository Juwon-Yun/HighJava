package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest02 {

	public static void main(String[] args) {
		List<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010 - 1111 - 1111"));
		memList.add(new Member(5, "이순신", "010 - 2222 - 1111"));
		memList.add(new Member(6, "성춘향", "010 - 3333 - 1111"));
		memList.add(new Member(3, "강감찬", "010 - 4444 - 1111"));
		memList.add(new Member(4, "일지매", "010 - 5555 - 1111"));
		memList.add(new Member(2, "변학도", "010 - 6666 - 1111"));
	
		System.out.println("정렬전...");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("-------------------------------------------------");
		
		Collections.sort(memList);
		//내부정렬기준이 없는 데이터는 정렬을 할 수 없다.
		
		System.out.println("정렬 후....");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("-------------------------------------------------");
		
		Collections.sort(memList, new SortName());
//		Collections.sort(memList, Collections.reverseOrder());
		
		System.out.println("외부 정렬 후...");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("-------------------------------------------------");
	}
}

// Member의 num값의 내림차순으로 정렬할 수 있는 내부 정렬 기준 설정하기
// 내부정렬기준 Comparable
// 외부정렬기준 Comparator
class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;
	
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 현재 객체와 매개변수 mem의 값을 비교함
	// num값의 내림차순
	
	@Override
	public int compareTo(Member mem) {
//		if(this.getNum()	 >  mem.getNum()) {
//			return -1; 
//		}else if(this.getNum() < mem.getNum()) {
//			return 1; // 양수를 반환하면 두 값의 순서가 바뀐다.
//		}else {
//			return 0;
//		}
		
		//	Wrapper 클래스를 이용하는 방법1
//		return new Integer(this.getNum()).compareTo(mem.getNum() * -1 );
		
		//	Wrapper 클래스를 이용하는 방법2
		return Integer.compare(this.getNum(), mem.getNum()) * -1;
		//오름차순 정렬 기준  * -1 
		 
	}
	
}

//외부 정렬 기준 => 회원 이름 오름차순 정렬 될 수 있는 외부 정렬 기준 만들기
class SortName implements Comparator<Member>{
	
	@Override
	public int compare(Member mem1, Member mem2) {
		return mem1.getName().compareTo(mem2.getName());
	}
}