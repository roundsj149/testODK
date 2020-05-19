package com.ja.freeboard.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.freeboard.mapper.AuthSQLMapper;
import com.ja.freeboard.mapper.HobbySQLMapper;
import com.ja.freeboard.mapper.MemberSQLMapper;
import com.ja.freeboard.util.FBMessageDigest;
import com.ja.freeboard.vo.*;
import java.security.*;	// 비밀번호 암호화!

@Service
public class MemberServiceImpl {
	
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	@Autowired
	private HobbySQLMapper hobbySQLMapper;
	@Autowired
	private AuthSQLMapper authSQLMapper;
	
	
	public void joinMember(MemberVo memberVo, int[] member_hobby, AuthVo authVo) {
		
		// 비밀번호 해싱 -> 클래스 따로 만듦
		String hashCode = FBMessageDigest.digest(memberVo.getMember_pw());
		memberVo.setMember_pw(hashCode);
		
		// DB 연동
		int member_key = memberSQLMapper.createKey();
		
		memberVo.setMember_no(member_key);
		memberSQLMapper.insert(memberVo);
		
		authVo.setMember_no(member_key);
		authSQLMapper.insert(authVo);
		
		if(member_hobby == null) {
			return;
		}
		
		for(int hobby : member_hobby) {
			
			hobbySQLMapper.insert(member_key, hobby);
		}
	}
	
	public MemberVo login(MemberVo memberVo) {

		String hashCode = FBMessageDigest.digest(memberVo.getMember_pw());
		memberVo.setMember_pw(hashCode);
		
		return memberSQLMapper.selectByIdAndPw(memberVo);
	}
	
	public void certification(String key) {
		
		authSQLMapper.update(key);
	}
	
	public boolean confirmId(String id) {
		
		if(memberSQLMapper.selectById(id) == null) {
			
			return true;	// 중복되는 아이디 없음 - 사용 가능
		}
		return false;	// 중복되는 아이디 존재 - 사용 불가능
	}
}


















