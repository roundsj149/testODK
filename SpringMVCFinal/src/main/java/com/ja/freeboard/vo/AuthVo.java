package com.ja.freeboard.vo;

public class AuthVo {
	private int auth_no;
	private int member_no;
	private String auth_certification;
	private String auth_key;
	
	public AuthVo() {
		super();
	}

	public AuthVo(int auth_no, int member_no, String auth_certification, String auth_key) {
		super();
		this.auth_no = auth_no;
		this.member_no = member_no;
		this.auth_certification = auth_certification;
		this.auth_key = auth_key;
	}

	public int getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(int auth_no) {
		this.auth_no = auth_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getAuth_certification() {
		return auth_certification;
	}

	public void setAuth_certification(String auth_certification) {
		this.auth_certification = auth_certification;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}	
}