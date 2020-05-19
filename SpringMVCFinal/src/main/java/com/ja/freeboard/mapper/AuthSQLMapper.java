package com.ja.freeboard.mapper;

import com.ja.freeboard.vo.AuthVo;

public interface AuthSQLMapper {

	public void insert(AuthVo authVo);
	
	public void update(String key);
	
	public AuthVo selectByMemberNo(int member_no);
}