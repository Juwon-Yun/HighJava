package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		/*
		 	Map => key값과 value값을 한 쌍으로 관리하는 객체
		 			- key값은 중복을 허용하지 않고 순서가 없다. (Set의 특징을 갖는다)
		 			- value값은 중복을 허용한다.
		 */
		Map<String, String> map = new HashMap<>();
		// 자료 추가 => put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println(map);
		
		//자료수정 => 데이터를 추가할 때 key값이 같으면
		//						나중에 추가한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map => " + map);

		//자료삭제 =>  remove(key값) : key값이 같은 자료를 찾아서 삭제한다.
		//    					반환값: 삭제된 자료의 value값
		
//		String removeTel = map.remove("tel");
//		System.out.println("key값이 tel인 value값을 리턴한 결과: "+removeTel);
//		System.out.println("map => " + map);
//		
		
		//자료읽기 => get(key값) : key값과 같이 저장된 value값을 반환
		System.out.println("이름: " + map.get("name"));
		System.out.println("나이: " + map.get("age"));
		
		// key 값의 존재 여부를 나타내는 메서드 : containsKey(key값)
		//				=> 지정한 key값이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부: " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부: " + map.containsKey("age"));
		
		//Map에 저장된 모든 데이터를 읽어와 처리하기
		
		//방법1) keySet( ) 메서드 이용하기
		//			 => Map의 모든 key값을 읽어와 Set형으로 반환
		
		Set<String> keySet = map.keySet();
		
		Iterator<String> it = keySet.iterator();
		System.out.println("================================");
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : "  + map.get(key));
		}
		System.out.println("================================");
		
		// 방법2) KeySet을 향상된 for문으로 사용하기
		
		for (String key : map.keySet()) {
			System.out.println(key + " :  " + map.get(key));
		}
		System.out.println("================================");
		
		//방법3) value값만 읽어와 출력하기 => values( ) 메서드 이용하기
		
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("================================");
		//방법4) Map의 내부클래스 Entry 활용(가장 Map스러운 방식이다)
		/*
		 	Map에는 Entry라는 내부 클래스가 만들어져 있다.
		 	이 Entry클래스에는 key와 value가 멤버변수로 있다.
		 	Map에서 이 Entry 클래스들을 Set형식으로 저장하여 관리한다.
		 	참고: Entry는 Map 아니면 쓸 데가 없기 때문에 내부 클래스로 선언함
		 */
		
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryit = mapSet.iterator();
		
		while(entryit.hasNext()) {
			Map.Entry<String, String> entry = entryit.next();
			
			System.out.print("Key값: " + entry.getKey());
			System.out.println(" // value값: " + entry.getValue());
		}
		System.out.println("================================");
		for (Entry<String, String> entry : mapSet) {
			System.out.println(entry);
		}
		System.out.println("================================");
		
		
		
		
		
		
		
		
		
		
		
	}
}
