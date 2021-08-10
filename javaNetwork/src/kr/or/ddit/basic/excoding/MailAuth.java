package kr.or.ddit.basic.excoding;


import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator {

	PasswordAuthentication pa;
	// 메일 권한 인증하기
	public MailAuth() {

		String mail_id = "yjw32123@gmail.com";  // 메일주소

		String mail_pw = "@a741852"; //비밀번호

		pa = new PasswordAuthentication(mail_id, mail_pw);

	}

	public PasswordAuthentication getPasswordAuthentication() {

		return pa;

	}

}

