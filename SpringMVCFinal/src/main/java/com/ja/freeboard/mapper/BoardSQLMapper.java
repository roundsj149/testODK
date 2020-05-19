package com.ja.freeboard.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.ja.freeboard.vo.*;

public interface BoardSQLMapper {

	// SQL 처리 (방법 2) - xml 만들기(src/main/resources에 폴더 만들기 - 패키지 구조랑 똑같이! 파일명도 똑같이!) / Query 길 때 작성하기 좋음(보기 편함)
	
	public int createKey();
	
	// 글쓰기
	public void insert(BoardVo boardVo);
	
	// 글 하나 읽기
	public BoardVo selectByNo(int no);
	
	// 페이징 - 글 개수 세기
	public int selectAllCount();
	
	public int selectByTitleCount(String search_word);	
	
	// ArrayList -> List 가능(다형성) / ArrayList를 LinkedList로 바꾸고 싶을 때 return type 다 바꿔줘야하는 문제가 있으므로 일반적으로 List로 함
	// 글 목록
	public List<BoardVo> selectAll(int curr_page);
	
	// 글 삭제
	public void deleteByNo(int board_no);

	// 글 수정
	public void update(BoardVo boardVo);
	
	// 카운트
	public void updateReadCount(int board_no);
	
	// 제목 검색
	// mybatis에서 매개변수 2개 이상 받을 때 @Param 이용(""안의 이름이 input type의 name임)
	public List<BoardVo> selectByTitle(@Param("board_title")String board_title, @Param("curr_page")int curr_page); 
}