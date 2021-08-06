package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 객체를 파일에 저장하는 예제
public class ObjectIOTest {
	
	public static void main(String[] args) {
		// Member 객체 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "인천");
		Member mem4 = new Member("홍길북", 50, "강릉");
		
		try {
			//1. 직렬화 : 객체를 파일에 저장하기 (Output)
			//객체를 파일에 저장하기
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			
			//객체 저장하기
			System.out.println("객체 저장하기 시작!!!");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장하기 끝!!!");
			
			
			
			//=================================
			
			// 2. 역직렬화 : 저장한 객체를 읽어와 콘솔에 출력하기 (Input)
			ObjectInputStream ois= new ObjectInputStream
					(new BufferedInputStream
							(new FileInputStream("d:/d_other/memObj.dat")));
			try {
				System.out.println("객체 읽기 작업 시작!!!");
				Object obj;
				
				//readObject( ) 메소드는 Exception을 추가해야하고
				// 데이터를 끝까지 다 읽어오면 EOF(end of file)Exception이 발생한다.
			while( (obj = ois.readObject()) != null) {
				//읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
				Member mem = (Member)obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("-----------------------------");
			}
			}catch (EOFException e) {
				System.out.println("객체 읽기 작업 끝!!!");
//				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				oos.close();
				ois.close();
			}
			
			
		}catch (IOException e) {
			//더이상 읽어올 객체가 없으면 예외 발생함.
			System.out.println("직렬화or 역직렬화 실패!!!");
		}
}
}

// 직렬화 했을때의 객체와 역직렬화 했을때의 객체가 버전이 맞는지

class Member implements Serializable{
	private static final long serialVersionUID = -2223171451786155938L;

	// transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	// 				  => 직렬화가 되지 않은 멤버변수는 기본값으로 저장된다.
	//					(참조변수 => null, 숫자형 변수 => 0, 논리형 변수 => false)
	private String name;
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}