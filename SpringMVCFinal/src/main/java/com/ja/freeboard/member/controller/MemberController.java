package com.ja.freeboard.member.controller;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ja.freeboard.member.service.MemberServiceImpl;
import com.ja.freeboard.vo.*;

// 순수 데이터 처리가 아닌 것 controller에서 미리 처리
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired	// 컨테이너 조사해서 해당 class 연결해줌
	private MemberServiceImpl memberService;
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@RequestMapping("/login_page.do")
	public String loginPage() {
		
		return "member/login_page";
	}
	
	@RequestMapping("/join_member_page.do")
	public String joinMemberPage() {
		
		return "member/join_member_page";
	}
	
	@RequestMapping("/join_member_process.do")
	public String joinMemberProcess(MemberVo memberVo, int[] member_hobby) {
		
		// 인증키 생성
		AuthVo authVo = new AuthVo();
		String auth_key = UUID.randomUUID().toString();
		authVo.setAuth_key(auth_key);		
		
		memberService.joinMember(memberVo, member_hobby, authVo);
		// 메일 발송 - 메일 발송되는 동안 회원가입 완료 페이지 보여주기 위해 스레드 이용 - 클래스 만듦
		FBMailSenderThread thread = new FBMailSenderThread(memberVo.getMember_id(), auth_key, mailSender);
		thread.start();
		
		return "redirect:./login_page.do";			
	}
	
	@RequestMapping("/login_process.do")
	public String loginMemberProcess(MemberVo memberVo, HttpSession session) {	// 로그인 성공 시 세션 설정을 위해 매개변수에 HttpSession 넣어줌
		
		MemberVo userData = memberService.login(memberVo);
		
		if(userData == null) {	// 로그인 실패
			
			return "member/login_fail";
		} else {	// 로그인 성공
			
			session.setAttribute("sessionUser", userData);

			return "redirect:/board/main_page.do";	// 웹브라우저 기준의 /
		}
	}
	
	@RequestMapping("/certification_process.do")
	public String certificationProcess(String key) {
		
		memberService.certification(key);
		
		return "member/certification_complete";
	}
	
	@RequestMapping("/logout_process.do")
	public String logoutProcess(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/board/main_page.do";
	}
	
	@RequestMapping("/confirmId.do")
	@ResponseBody	// 리턴 값을 forwarding하지 않고 값(문자열) 자체를 그대로 넘겨줌
	public String confirmId(String id) {
		System.out.println(id);
		if(memberService.confirmId(id)) {
			
			return "true";
			
		} else {

			return "false";	
		}
	}
	
}

// 회원가입 시 인증 메일 보냄과 동시에 회원가입 완료 페이지 보여주기 위해 스레드 생성

// @Component	-> 해도 되지만 그냥 쓰레드 한번 써보려고 안씀: 코드상으로 직접 생성하고 써보기!
class FBMailSenderThread extends Thread {
	
	private String to;
	private String auth_key;
//	@Autowired
	private JavaMailSender mailSender;
	
	public FBMailSenderThread(String to, String auth_key, JavaMailSender mailSender) {
		
		this.to = to;
		this.auth_key = auth_key;
		this.mailSender = mailSender;
	}
	
	public void run() {
		
		// 메일 발송 - 메일 발송되는 동안 회원가입 완료 페이지 보여주기 위해 스레드 이용 - 클래스 만듦
		try {
			
			MimeMessage message = null;
		    MimeMessageHelper messageHelper = null;
		    
		    message = mailSender.createMimeMessage(); // 메시지 만들기
	        
	        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	        messageHelper.setSubject("[WEB 발송] FB - 회원가입 본인 인증 메일입니다.");
	        
	        String link = "http://localhost:8181/freeboard/member/certification_process.do?key="+auth_key;
	        String text = "";
	        text += "FB - 회원가입을 축하드립니다.<br>";
	        text += "회원가입 완료를 위해 아래의 링크를 클릭해주세요.<br>";
	        text += "<a href='"+link+"'>";
	        text += "링크 클릭";
	        text += "</a>";
	        
	        messageHelper.setText(text, true);
	        messageHelper.setFrom("123", "admin");
	        messageHelper.setTo(to);	// 회원 가입자 아이디(이메일)
	        //messageHelper.addInline(contentId, dataSource);
	        mailSender.send(message);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


















