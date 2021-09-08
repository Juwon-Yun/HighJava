package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MymemberVO;

public class MymemberDaoImpl implements IMymemberDao {
	
private static MymemberDaoImpl instance;
	
	private SqlMapClient smc; 
	
	private MymemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClientFactory();
	}
	public static MymemberDaoImpl getInstance() {
		if( instance == null ) instance = new MymemberDaoImpl();
		return instance;
	}
	
	@Override
	public List<MymemberVO> SelectAll() {
		List<MymemberVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("member.getAllMember");
			if( list == null ) {
				System.out.println("리스트 널임 (dao)");
			}
		}catch(SQLException e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int checkMem(String id) {
		int mid = 0;
		try {
			mid = (int)smc.queryForObject("member.getMemberCount",id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return mid;
	}
	@Override
	public int insertMem(MymemberVO vo) {
		int count = 0;
		try {
			Object obj = smc.insert("member.insertMember",vo);
			if( obj == null ) {
				count = 1;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public MymemberVO getMember(String memId) {
		MymemberVO memVo = null;
		try {
			memVo = (MymemberVO)smc.queryForObject("member.getMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVo;
	}
	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember",memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int updateMember(MymemberVO mvo) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", mvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
