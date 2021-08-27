package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class JdbcToIbatis_Teacher {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SqlMapClient smc = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd;
			rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			int nextId = (int)smc.queryForObject("jdbc.selectMaxId");
			String gu="";
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = sc.nextLine();
				
				count = (int)smc.queryForObject("jdbc.getcountGu",gu);
				if( count > 0 ) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다. ");
					System.out.println("다시 입력하세요.");
				}
					
			} while (count > 0);
			System.out.println("상품 분류명(LPROD_NM) 입력 : ");
			String nm = sc.next();
			
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(nextId);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			Object obj = smc.insert("jdbc.insertLprod", lvo);
			
			if( obj == null ) {
				System.out.println("데이터 추가 성공");
			}else {
				System.out.println("데이터 추가 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}

}
