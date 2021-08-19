package kr.or.ddit.mvc.controller;

import java.sql.SQLException;

import kr.or.ddit.mvc.service.MemberServiceImpl;

public class MemberController {
	public static MemberServiceImpl msi;
	public static void main(String[] args) throws SQLException {
		msi = new MemberServiceImpl();
	}

}
