package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/*
 	Jdbc 공부할 때 사용했던 jdbcTest05.java 프로그램을 
 	ibatis용으로 변환하시오 
 	(SQL문이 저장될 xml문서의 파일명은 'jdbc.xml'로 한다.)
 	
	문제) LPROD테이블에 새로운 데이터를 추가하기
			LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고, 
			LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1만큼 크게 해서 처리한다.
			그리고 입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			
*/
public class JdbcToIbatis {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Charset charset = null;
		Reader rd = null;
		SqlMapClient smc = null;
		
		int count = 0;
			try {
				charset = Charset.forName("UTF-8");
				Resources.setCharset(charset);
				rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close();
				System.out.println("select 시작");
				count = (int) smc.queryForObject("jdbc.selectMaxId");
				System.out.println( count );
				
				while(true) {
					try {
						System.out.println("insert 시작");
						
						System.out.print("Lprod_gu 입력 : ");
						String gu = sc.next();
						System.out.print("Lprod_nm 입력 : ");
						String nm = sc.next();
						
						LprodVO lvo = new LprodVO();
						lvo.setLprod_id( count );
						lvo.setLprod_gu(gu);
						lvo.setLprod_nm(nm);
						
						Object obj = smc.insert("jdbc.insertLprod", lvo);
						
						if( obj == null ) System.out.println("insert 작업 성공"); break;
						
					}catch(SQLException e) {
						System.out.println("중복되는 lprod_gu값 입니다 다시 입력하세요");
					}
				}//while문
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				sc.close();
			}
			
	}
}