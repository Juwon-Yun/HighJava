package excoding;

public class NonGenericClassTest02 {

	public static void main(String[] args) {
		NonGenericClassTest ng1 = new NonGenericClassTest();
		ng1.setVal("가나다라");
		NonGenericClassTest ng2 = new NonGenericClassTest();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		Integer irtNg2 = (Integer)ng2.getVal();
		
		Mygeneric<String> mg1 = new Mygeneric<>();
		Mygeneric<Integer> mg2 = new Mygeneric<>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getval();
		irtNg2 = mg2.getval();
		
		System.out.println(rtnNg1);
		System.out.println(irtNg2);
	}
}
