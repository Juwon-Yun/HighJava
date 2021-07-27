package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashcodeTest {
	public static void main(String[] args) {
		
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1);
		p2.setName("홍길동");
		
		
		Person p3 = p1;
		
		System.out.println(p1==p2);
		System.out.println(p3==p1);
		System.out.println(p1.equals(p2));
		System.out.println();
		
		System.out.println(p1);
		System.out.println(p2.toString());
		System.out.println("============================");
		
		Set<Person>testSet =new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		//HashSet에 HashCode를 재정의하면 크기는 1, 재정의 안하면 2
		//Set은 HashCode로 객체를 비교한다.
		System.out.println("Set의 크기: " + testSet.size());	
		System.out.println("Set의 값" + testSet);
		//재정의하면 같고 Object를 상속받은 상태면 서로 다르다.
		System.out.println("p1의 hashcode : "  + p1.hashCode());
		System.out.println("p2의 hashcode : "  + p2.hashCode());
		
	}
}
class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//같은 유형의 클래스인지 검사
		if (getClass() != obj.getClass())
			return false;
		//클래스 검사
		
		//클래스ok 됬으니 데이터 검사
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Person의 아이디=" + id + ", 이름= " + name + "입니다";
	}
	
	
}