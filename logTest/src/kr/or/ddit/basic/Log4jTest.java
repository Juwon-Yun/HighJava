package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger클래스의 인스턴스를 받아온다.
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		// 로그 메세지 출력하기
		// 형식1) logger객체변수.레벨이름("출력할 메세지");
		// 형식2) logger.log(level.레벨이름, "출력할 메세지");
		logger.trace("이것은 log4j의 [TRACE]레벨의 메세지입니다.");
		logger.debug("이것은 log4j의 [DEBUG]레벨의 메세지입니다.");
		logger.info("이것은 log4j의 [INFO]레벨의 메세지입니다.");
		logger.warn("이것은 log4j의 [WARN]레벨의 메세지입니다.");
		logger.error("이것은 log4j의 [ERROR]레벨의 메세지입니다.");
		logger.fatal("이것은 log4j의 [FATAL]레벨의 메세지입니다.");
		
		logger.log(Level.DEBUG, "debug레벨의 메세지");
	}

}
