package com.ja.freeboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.ja.freeboard.vo.*;

public interface HobbySQLMapper {
	
	@Insert("INSERT INTO FB_Hobby VALUES(FB_Hobby_seq.nextval, #{member_no}, #{hobby_category_no})")
	
	// public void insert(HobbyVo member_hobby);
	
	// mybatis는 매개변수를 2개 이상 받기 위해 @을 붙여주어야 함
	public void insert(@Param("member_no") int member_no, @Param("hobby_category_no") int hobby_category_no);
}