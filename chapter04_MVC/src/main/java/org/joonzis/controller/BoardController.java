package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") // board에 들어오는 모든 것에 대해서
public class BoardController {
	@Autowired
	private BoardService service;
	
	// 전체 데이터 조회
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		log.info("list..." + cri);
		
		
		if(cri.getPageNum()==0 || cri.getAmount()==0) {
			cri.setPageNum(1);
			cri.setAmount(15);
		}
		
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal();
		
		log.info("total..."+total);
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		return "/board/list";
	}
	
	// 게시글 등록 화면 이동(boardList.js에서 get전송)
	@GetMapping("/register")
	public String registerPage() {
		return "/board/register";
	}
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO vo) {
		log.info("register..."+vo);
		service.register(vo);
		return "redirect:/board/list"; // 앞에 redirect:를 붙여서 페이지 이동 
	}
	
	// 조회
	@GetMapping("/get")
	public String get(@RequestParam("bno") int bno, Model model) {
		log.info("get..." + bno);
		model.addAttribute("vo", service.get(bno));
		return "/board/get";
	}
	
	// 수정 페이지
	@GetMapping("/modify")
	public String modifyPage(@RequestParam("bno")int bno, Model model) {
		log.info("nodify..."+bno);
		model.addAttribute("vo", service.get(bno));
		return "/board/modify";
	}
	
	// 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo) {
		log.info("modify..." + vo);
		service.modify(vo);
		return "redirect:/board/list";
	}
	
	// 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, Model model) {
		log.info("remove..." + bno);
		model.addAttribute("vo", service.remove(bno));
		return "redirect:/board/list";
	}
}
