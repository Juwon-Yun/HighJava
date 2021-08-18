package kr.or.ddit.basic.excoding;
//App.java

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String args[]) {
		sendMail();
	}

	/**
	 * SendMail
	 */
	public static void sendMail() {
		// 메일 인코딩
		final String bodyEncoding = "UTF-8"; // 콘텐츠 인코딩

		String subject = "메일 발송 테스트";
		String fromEmail = "yjw3212321@gmail.com";
		String fromUsername = "테스트주원";
		String toEmail = "yjw3212321@gmail.com"; // 콤마(,)로 여러개 나열

		// 보낼 Email 주소
		final String username = "yjw32123@gmail.com";
		// 2단계 앱 비밀번호
		final String password = "bdkjjgsxvvbrrfed";

		// 메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>안녕하세요 HTML 테이블예제입니다</h3>\n");
//		sb.append("<h4>개발하는 주원입니다.</h4>\n");
		sb.append("<table border = "+ 1 + ">\n");
		sb.append("<tr>\n");
		sb.append("<th>월요일</th>\n");
		sb.append("<th>화요일</th>\n");
		sb.append("<th>수요일</th>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td>C언어</td>\n");
		sb.append("<td>JAVA</td>\n");
		sb.append("<td>HTML</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td>C언어</td>\n");
		sb.append("<td>웹프로그래밍</td>\n");
		sb.append("<td>JAVA언어</td>\n");
		sb.append("</tr>\n");
		
		String html = sb.toString();

		// 메일 옵션 설정
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			// 메일 서버 인증 계정 설정
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			// 메일 세션 생성
			Session session = Session.getInstance(props, auth);

			// 메일 송/수신 옵션 설정
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromUsername));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
			message.setSubject(subject);
			message.setSentDate(new Date());

			// 메일 콘텐츠 설정
			Multipart mParts = new MimeMultipart();
			MimeBodyPart mTextPart = new MimeBodyPart();
			MimeBodyPart mFilePart = new MimeBodyPart();

			// 메일 콘텐츠 - 내용
			mTextPart.setText(html, bodyEncoding, "html");
			mParts.addBodyPart(mTextPart);
			
//			 ArrayList<String> al_attach = new ArrayList<>();
//			 al_attach.add("");
			
			
			mFilePart.setDataHandler(new DataHandler(new FileDataSource
					(new File("e:/d_other/스폰지밥.png"))));
			mParts.addBodyPart(mFilePart);
			
			// 메일 콘텐츠 설정
			message.setContent(mParts);

			// MIME 타입 설정
			MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(MailcapCmdMap);

			// 메일 발송
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
