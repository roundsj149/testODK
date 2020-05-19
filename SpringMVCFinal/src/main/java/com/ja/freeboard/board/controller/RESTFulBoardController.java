package com.ja.freeboard.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ja.freeboard.board.service.BoardServiceImpl;
import com.ja.freeboard.vo.MemberVo;
import com.ja.freeboard.vo.ReplyVo;

@Controller
@ResponseBody	// 하위의 모든 메소드 포워딩 안함
@RequestMapping("/board/*")
public class RESTFulBoardController {	// forwarding 안함
	
	@Autowired
	private BoardServiceImpl boardService;

	@RequestMapping("/write_reply_process.do")
	public String writeReplyProcess(ReplyVo replyVo, HttpSession session) {
		
		int member_no = ((MemberVo)session.getAttribute("sessionUser")).getMember_no();
		
		replyVo.setMember_no(member_no);
		
		boardService.writeReply(replyVo);
		
		return "true";
	}
	
	@RequestMapping("/get_reply_list.do")
	public List<Map<String,Object>> getReplyList(int board_no) {
		
		List<Map<String,Object>> list = boardService.getReplyList(board_no);
		
		/* -> JSON 구조: pom.xml에 dependency 추가해서 자동으로 만들어줌
		[
		{"memberVo": {"member_no": 1, "member_id": "roundsj@naver.com", .....}, "replyVo": {"reply_no":1, "board_no": 10, .......}},
		{},
		{},
		{}
		....
		]
		*/
		
		return list;
	}
	
}
