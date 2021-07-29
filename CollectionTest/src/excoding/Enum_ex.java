package excoding;

public class Enum_ex {
	
	public enum Season{
		봄, 여름, 가을, 겨울
	}
	public static void main(String[] args) {
		Season	season = Season.봄;
		System.out.println(season);
		
		Season season2 = Season.가을;
		int result1 = season.compareTo(season2);
		System.out.println(result1);
		
		season = Season.겨울;
		int result2 = season.compareTo(season2);
		System.out.println(result2);
	
		season2 = Season.여름;
		int result3 = season.compareTo(season2);
		System.out.println(result3);
	}

}
