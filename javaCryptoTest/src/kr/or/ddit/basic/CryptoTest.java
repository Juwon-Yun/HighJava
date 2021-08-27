package kr.or.ddit.basic;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {
	public static void main(String[] args) {
		try {
			String plainText = "Hello, world!! 안녕하세요 12345&*()_+";
			// 암호화 키 값 설정 (임의로 16자리 이상)
			String key = "a1b2c3d4e5f6g7h8";
			
			System.out.println("원본 문자열 : " + plainText);
			
			String en = CryptoUtil.sha512(plainText);
			System.out.println("단방향 : " + en );
			System.out.println("단방향 길이 : " + en.length() );
			
			// 암호화 하기
			String enStr = CryptoUtil.encryptAES256(plainText, key);
			System.out.println("암호화 : " + enStr );
			System.out.println("암호화 길이 : " + enStr.length() );
			
			// 암호화된 데이터 enStr을 넣어준다
			System.out.println("복호화 : " + CryptoUtil.decryptAES256(enStr, key));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
