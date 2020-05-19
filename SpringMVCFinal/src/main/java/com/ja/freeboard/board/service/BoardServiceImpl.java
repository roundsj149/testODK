package com.ja.freeboard.board.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.freeboard.mapper.*;
import com.ja.freeboard.vo.*;

@Service
public class BoardServiceImpl {

	@Autowired
	private BoardSQLMapper boardSQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	@Autowired
	private UploadFileSQLMapper uploadFileSQLMapper;
	@Autowired
	private ReplySQLMapper replySQLMapper;
	
	public void writeContent(BoardVo boardVo, List<UploadFileVo> fileVoList) {
		
		int boardKey = boardSQLMapper.createKey();
		
		boardVo.setBoard_no(boardKey);
		boardSQLMapper.insert(boardVo);
		
		for(UploadFileVo uploadFileVo : fileVoList) {
			
			// uploadFileVo.setBoard_no(boardVo.getBoard_no());		-> 동시접속 시 문제 발생
			uploadFileVo.setBoard_no(boardKey);
			uploadFileSQLMapper.insert(uploadFileVo);
		}
	}
	
	public int getBoardDataCount(String search_word) {
		if(search_word == null) {
			return boardSQLMapper.selectAllCount();
		} else {
			return boardSQLMapper.selectByTitleCount(search_word);
		}
	}
	
	public List<Map<String,Object>> getBoardList(String search_word, int curr_page) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();	// List는 인터페이스이므로 new List 불가능
		List<BoardVo> boardList = null;
		
		if(search_word == null) {
			
			boardList = boardSQLMapper.selectAll(curr_page);
		} else {
			
			boardList = boardSQLMapper.selectByTitle(search_word, curr_page);
		}
		
		for(BoardVo boardVo : boardList) {
			
			MemberVo memberVo = memberSQLMapper.selectByNo(boardVo.getMember_no());
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("boardVo", boardVo);
			
			list.add(map);
		}
		return list;
	}
	
	public Map<String,Object> readContent(int board_no) {
				
		Map<String,Object> map = new HashMap<String,Object>();
		
		BoardVo boardVo = boardSQLMapper.selectByNo(board_no);
		MemberVo memberVo = memberSQLMapper.selectByNo(boardVo.getMember_no());
		
		List<UploadFileVo> uploadFileVoList = uploadFileSQLMapper.selectByBoardNo(board_no);
		
		map.put("boardVo", boardVo);
		map.put("memberVo", memberVo);
		map.put("uploadFileVoList", uploadFileVoList);
		
		return map;
	}
	
	public List<UploadFileVo> readFileList(int file_no) {
		
		List<UploadFileVo> fileVoList = uploadFileSQLMapper.selectByFileNo(file_no);
		
		return fileVoList;
	}
	
	
	public void modifyContent(BoardVo boardVo) {
		
		boardSQLMapper.update(boardVo);
	}
	
	public void updateReadCount(int board_no) {
		
		boardSQLMapper.updateReadCount(board_no);
	}
	
	public void deleteContent(int board_no) {
		
		boardSQLMapper.deleteByNo(board_no);
	}
	
	public List<Map<String,Object>> getReplyList(int board_no) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		List<ReplyVo> replyVoList = replySQLMapper.selectByBoardNo(board_no);
		
		for(ReplyVo replyVo : replyVoList) {
			MemberVo memberVo = memberSQLMapper.selectByNo(replyVo.getMember_no());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("replyVo", replyVo);
			map.put("memberVo", memberVo);
			
			list.add(map);
		}
		return list;
	}
	
	public void writeReply(ReplyVo replyVo) {
		
		replySQLMapper.insert(replyVo);	// board_no, member_no
	}
}












