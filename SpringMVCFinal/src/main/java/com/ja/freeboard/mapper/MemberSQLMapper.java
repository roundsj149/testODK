package com.ja.freeboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ja.freeboard.vo.*;

public interface MemberSQLMapper {
	
	// 하나의 값이 동시에 둘 이상의 테이블에 들어가는 경우 - select nextval 먼저 호출해서 키값을 만들고 membervo와 hobby에 넣음
	// SQL 처리 (방법 1) - 가장 쉽고 편함. 잘 안씀
	@Select("SELECT FB_Member_seq.nextval FROM DUAL")
	public int createKey();
	
	// #{ } getter를 이용해서 값을 가져옴 - 매개변수로 넘어온 값!
	@Insert("INSERT INTO FB_Member VALUES(#{member_no}, #{member_id}, #{member_pw}, #{member_nick}, #{member_sex}, SYSDATE)")
	public void insert(MemberVo memberVo);
	
	@Select("SELECT * FROM FB_Member m, FB_Member_Auth a WHERE m.member_no = a.member_no AND m.member_id=#{member_id} AND m.member_pw=#{member_pw} AND a.auth_certification='Y'")
	public MemberVo selectByIdAndPw(MemberVo memberVo);	// 매개변수와 return의 MemberVo는 다른 인스턴스임! 헷갈리지 말것!
	
	@Select("SELECT* FROM FB_Member WHERE member_no=#{member_no}")
	public MemberVo selectByNo(int member_no);
	
	@Select("SELECT * FROM FB_Member WHERE member_id=#{id}")
	public MemberVo selectById(String id);
}