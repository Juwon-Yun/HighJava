package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 => IP 주소를 다루기 위한 클래스 
		
		// www.naver.com 사이트의 IP 정보 가져오기 
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostNmae => " + naverIp.getHostName());
		System.out.println("HostAddress => " + naverIp.getHostAddress());
		System.out.println("toString => " + naverIp.toString());
		System.out.println();
		
		// 자신의 컴퓨터의 IP정보 가져오기 
		InetAddress localIp = InetAddress.getLocalHost();
		
		System.out.println("내 컴퓨터의 HostName => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 HostAddress ->" + localIp.getHostAddress());
		System.out.println();
		
		//아이피가 여러개인 호스트의 정보 가져오기
		InetAddress [] ipArr = InetAddress.getAllByName("www.naver.com");
		for (InetAddress ip : ipArr) {
			System.out.println("네이버 아이피 : "+ip);
		}
		System.out.println();
		InetAddress [] ipArr2 = InetAddress.getAllByName("www.google.com");
		for (InetAddress ip : ipArr2) {
			System.out.println("구글 아이피 : "+ip);
		}
		
	}
}
