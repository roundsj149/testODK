package com.ja.freeboard.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ja.freeboard.board.service.BoardServiceImpl;
import com.ja.freeboard.vo.*;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	static String uploadRootFolderName = "c:/upload/";
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("/main_page.do")
	public String mainPage(String search_word, Model model, @RequestParam(value="curr_page", required = false, defaultValue = "1") int curr_page) {
		// 기본타입은 널값 못날려줌 - requestParam. value="" ->파라미터 명 / required = true이면 무조건 필요한 경우 / defaultValue = 안날아왔을 때 처리 (1로 세팅)
		
		/* defaultValue = "1"로 설정해줬으므로 필요없는 코드가 됨
		if(curr_page <= 0) {
			curr_page=1;
		}
		*/
		
		List<Map<String,Object>> list = boardService.getBoardList(search_word, curr_page);
			
		int totalCount = boardService.getBoardDataCount(search_word);
		
		// 페이지바가 5씩 나오도록 (1~5, 6~10, 11~15)
		int beginPage = ((curr_page-1)/5)*5 + 1;
		int endPage = ((curr_page-1)/5+1)*5;
		
		// 글 개수에 따라 마지막 페이지 숫자가 달라짐
		if(endPage > ((totalCount-1)/10+1)) {
			endPage = (totalCount-1)/10+1;
		}
		
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("curr_page", curr_page);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("dataList", list);
			
		return "board/main_page";
	}
	
	@RequestMapping("/write_content_page.do")
	public String writeContentPage() {
		return "board/write_content_page";
	}
	
	@RequestMapping("/write_content_process.do")
	// MultipartFile: 파일 받기 위한 API. uploadfiles=파라미터명(파라미터명과 변수명 똑같이 지어줘야 함) - 파일 여러 개일 수 있으므로 배열로 받음
	public String writeContentProcess(MultipartFile[] upload_files, BoardVo boardVo, HttpSession session) {

		// 파일 업로드
		// 업로드된 파일 저장 경로
		
		
		// 하나의 폴더에 다 저장되도록 하면 복잡해짐 -> 날짜별로 폴더를 자동 생성되도록 해서 이곳에 저장되도록
		Date today = new Date();	// 오늘 날짜 나옴
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");	// Date to String
		String todayFolder= df.format(today);
		
		String saveFolderName = uploadRootFolderName + todayFolder;	// 업로드된 파일 저장될 폴더 만듦
		
		File saveFolder = new File(saveFolderName);	// File: directory 관리
		
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();	// mkdir: 폴더 하나 만듦. mkdirs: 폴더 경로대로 만듦
		}
		
		List<UploadFileVo> fileVoList = new ArrayList<UploadFileVo>();
		
		// 업로드된 파일 하나씩 받아오기
		for(MultipartFile file : upload_files) {
			// 예외처리: 파일 없을 때 0이 들어가기 때문
			if(file.getSize() <= 0) {
				continue;
			}

			// 파일 중복을 막기 위해 저장할 때 이름 바뀌어서 저장되도록 함. 원래 파일명에 시간(ms단위)과 랜덤숫자 붙임
			String saveFileName = UUID.randomUUID().toString();	// 랜덤숫자 - math.random() 써도 됨
			
			saveFileName += "_" + System.currentTimeMillis();	// 시간 추가
			
			String originalFileName = file.getOriginalFilename();
			
			saveFileName += originalFileName.substring(originalFileName.lastIndexOf("."));	// 확장자 추가
			
			// "/" 넣어줘야 c:/upload/20200514/파일명이 됨. 없으면  c:/upload/20200514파일명이 됨
			String saveRealPath = saveFolderName + "/" + saveFileName;
			
			try {
				file.transferTo(new File(saveRealPath));	// File객체: file 관리
				
				System.out.println("test getSize: "+file.getSize());
				System.out.println("test getOriginalFilename: "+file.getOriginalFilename());
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// DB를 위한 UploadFileVo객체 생성
			UploadFileVo uploadFileVo = new UploadFileVo();
			uploadFileVo.setFile_link_path(todayFolder + "/" + saveFileName);	// /2020/05/14/파일명
			uploadFileVo.setFile_real_path(saveRealPath);
			
			fileVoList.add(uploadFileVo);
		}
		
		// 데이터 처리
		MemberVo memberVo = (MemberVo)session.getAttribute("sessionUser");
		
		boardVo.setMember_no(memberVo.getMember_no());
		
		boardService.writeContent(boardVo, fileVoList);
		
		return "redirect:./main_page.do";
	}
	
	@RequestMapping("/read_content_page.do")
	public String readContentPage(int board_no, Model model, HttpSession session) {
		
		Map<String,Object> boardContent = boardService.readContent(board_no);

		BoardVo boardVo = (BoardVo)boardContent.get("boardVo");
		
		if(session.getAttribute("sessionUser") != null) {
			
			MemberVo memberVo = (MemberVo)session.getAttribute("sessionUser");
			
			if(boardVo.getMember_no() != memberVo.getMember_no()) {
				
				boardService.updateReadCount(board_no);
				boardContent = boardService.readContent(board_no);	// 글 읽자마자 조회수 올라가게 해줌
			}
		}
		
		model.addAttribute("boardContent", boardContent);
		
		return "board/read_content_page";
	}	
	
	@RequestMapping("/modify_content_page.do")
	public String modifyContentPage(int board_no, Model model) {
		
		Map<String,Object> boardContent = boardService.readContent(board_no);
		model.addAttribute("boardContent", boardContent);
		
		return "board/modify_content_page";
	}
	
	@RequestMapping("/modify_content_process.do")
	public String modifyContentProcess(BoardVo boardVo) {
		
		boardService.modifyContent(boardVo);
		
		return "redirect:/board/main_page.do";
	}
	
	@RequestMapping("/delete_content_process.do")
	public String deleteContentPage(int board_no) {
		
		boardService.deleteContent(board_no);
		
		return "redirect:/board/main_page.do";
	}
	
	 // 첨부파일 다운로드
    @RequestMapping("/file_download_process.do")                      
    public ModelAndView fileDownload(@RequestParam("filePath") String filePath) {

	File file = new File(filePath);
	
	// fileDownloadUtil: java파일   / 키:값 - downloadFile:file
	return new ModelAndView("fileDownloadUtil", "downloadFile", file);
	//("download", "downloadFile", file);
	}
    
    
    
    
    
    
}