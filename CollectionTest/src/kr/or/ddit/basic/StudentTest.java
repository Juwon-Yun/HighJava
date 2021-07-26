package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를
 * 멤버로 갖는 Student클래스를 만든다.
 * 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수,
 * 수학점수만 매개변수로 설정해서 데이터를 초기화 한다.
 
 *	이 Student 객체는 List에 저장하여 관리한다.
 	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 
 	내부 정렬 기준을 구현하고, 
 	
 	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 
 	정렬이 되는 외부 정렬 기준 클래스를 작성하여 정렬한 결과를 출력하시오
 	
 	(등수는 List에 전체 데이터가 추가된 후에 구한다.)
 */

public class StudentTest {

	public static void main(String[] args) {
		//setRanking 메소드는 non static 메소드라서,
		//인스턴스 메소드이니 StudnetTest 객체를 생성해야함
		//	StudentTest set = new StudentTest();
		
		List<Student> stu = new ArrayList<>();

		stu.add(new Student(1, "홍길동", 74, 89, 92));
		stu.add(new Student(7, "강감찬", 67, 89, 97));
		stu.add(new Student(2, "이순신", 74, 96, 92));
		stu.add(new Student(5, "성춘향", 74, 96, 92));
		stu.add(new Student(4, "일지매", 74, 89, 23));
		stu.add(new Student(3, "변학도", 74, 85, 92));
		stu.add(new Student(6, "김철수", 34, 89, 92));
		rank(stu);
		
		//등수를 구하는 메서드를 호출한다
//		set.setRanking(stu);
		
		Collections.sort(stu);

		System.out.println("학번을 오름차순으로 정렬한 후");
		for (Student student : stu) {
			System.out.println(student);
		}

		Collections.sort(stu, new sumDesc());

		System.out.println("총점을 내림차순을 정렬한 후");
		for (Student student : stu) {
			System.out.println(student);
		}

	}

	private static void rank(List<Student> stu) {
		for (int i = 0; i < stu.size(); i++) {
			for (int j = 0; j < stu.size(); j++) {
				if (stu.get(i).getSum() < stu.get(j).getSum()) {
					int rank = stu.get(i).getRank();
					rank += 1;
					stu.get(i).setRank(rank);
				}
			}
		}
	}
	
//	public void setRanking(List<Student> stdList) {
//		for (Student std1 : stdList) {	//기준이 되는 데이터 개수만큼 반복
//			int rank = 1;	//등수가 저장될 변수(처음에는 1등으로 셋팅한다.)
//			for (Student std2 : stdList) {	//비교 대상을 나타내는 반복문
//				
//				//기준보다 큰 값을 만나면 등수를 증가시킨다.
//				if(std1.getSum() < std2.getSum()) {
//					rank++;
//				}
//			}
//			
//			//구해진 등수를 Student 객체의 rank변수에 저장한다.
//			std1.setRank(rank);
//		}
//	}
}

class Student implements Comparable<Student> {

	private int studentNum;
	private String studentName;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(int studentNum, String studentName, int kor, int eng, int math) {
		super();
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
		this.rank = 1;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", studentName=" + studentName + ", kor=" + kor + ", eng=" + eng
				+ ", math=" + math + ", sum=" + sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student studentNum) {
		return Integer.compare(this.getStudentNum(), studentNum.getStudentNum());
	}

}

class sumDesc implements Comparator<Student> {
	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getSum() > stu2.getSum()) {
			return -1;
		} else if (stu1.getSum() < stu2.getSum()) {
			return 1;
		} else {
			if (stu1.getStudentName().compareTo(stu2.getStudentName()) > 0) {
				return -1;
			} else if (stu1.getStudentName().compareTo(stu2.getStudentName()) < 0) {
				return 1;
			} else
				return 0;
		}
	}
}
