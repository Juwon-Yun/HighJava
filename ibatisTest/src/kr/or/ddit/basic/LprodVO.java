package kr.or.ddit.basic;

public class LprodVO {
	private int lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	
	// ibatis에서 LprodVO를 생성하게할때 기본생성자를 가지고 만들기 때문에 
	// 기본생성자를 만들어줘야 한다. (인위적인 생성자를 만들때)
	public LprodVO() {
		
	}
	
	// 인위적인 생성자
	public LprodVO(int lprod_id, String lprod_gu, String lprod_nm) {
		super();
		this.lprod_id = lprod_id;
		this.lprod_gu = lprod_gu;
		this.lprod_nm = lprod_nm;
	}

	public int getLprod_id() {
		return lprod_id;
	}

	public void setLprod_id(int lprod_id) {
		this.lprod_id = lprod_id;
	}

	public String getLprod_gu() {
		return lprod_gu;
	}

	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}

	public String getLprod_nm() {
		return lprod_nm;
	}

	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	
	
}
