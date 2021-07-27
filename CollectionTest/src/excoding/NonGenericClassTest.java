package excoding;

public class NonGenericClassTest {
	 private Object val;
	 public Object getVal() {
		 return val;
	 }
	 public void setVal(Object val) {
		 this.val = val;
	 }


}//nongeneric

class Mygeneric<T>{
	private T val;
	public T getval() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}
}//mygeneric
