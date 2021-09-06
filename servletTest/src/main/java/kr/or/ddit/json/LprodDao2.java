package kr.or.ddit.json;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LprodDao2 {
	private SqlMapClient smc;
	
	public LprodDao2() {
		smc = SqlMapClientFactory.getSqlMapClientFactory();
	}
	
	public List<LprodVO> getLprodList(){
		List<LprodVO> lprodList = null;
		try {
			lprodList = smc.queryForList("Lprod.getAllLprod");
		}catch (SQLException e) {
			lprodList = null;
			e.printStackTrace();
		}
		return lprodList;
	}
}
