package com.ja.freeboard.vo;

public class HobbyVo {
	
	private int hobby_no;
	private int member_no;
	private int hobby_category_no;
	
	public HobbyVo() {
		super();
	}

	public HobbyVo(int hobby_no, int member_no, int hobby_category_no) {
		super();
		this.hobby_no = hobby_no;
		this.member_no = member_no;
		this.hobby_category_no = hobby_category_no;
	}

	public int getHobby_no() {
		return hobby_no;
	}

	public void setHobby_no(int hobby_no) {
		this.hobby_no = hobby_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getHobby_category_no() {
		return hobby_category_no;
	}

	public void setHobby_category_no(int hobby_category_no) {
		this.hobby_category_no = hobby_category_no;
	}
}