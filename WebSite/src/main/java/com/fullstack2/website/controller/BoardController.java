package com.fullstack2.webSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack2.webSite.dtos.QnABoardDTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.service.QnABoardService;

import lombok.RequiredArgsConstructor;
import lombok.var;

@RequestMapping("/board/")
@Controller
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private final QnABoardService qnaBoardService;
	
    @GetMapping("/list")
    public void list(@ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO, Model model) {
        // Model에게 List에서 뿌려질 항목 DTO를 전달합니다.
        model.addAttribute("result", qnaBoardService.getList(qnaPageRequestDTO));
    }
    
    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(Long bno, String password) {
        // bno와 password를 사용하여 DB에서 비밀번호를 확인하는 로직을 구현
        // 일치하면 true, 일치하지 않으면 false 반환
        boolean isPasswordCorrect = qnaBoardService.checkPassword(bno, password);
        return isPasswordCorrect;
    }
    
    //신규 글 등록 폼 요청처리하기
  	@GetMapping("/register")
  	public void register() {
  		
  	}
  	
  	//신규 글 등록처리하기..
  	//글 등록은 service 를 통해서 하고, 새로 받은 board 의 글 번호는 redirect 로 list
  	//페이지로 넘길게요
  	@PostMapping("/register")
  	public String register(QnABoardDTO dto, RedirectAttributes redirectAttributes) {
  		
  		//비밀번호 저장.
  		String password = dto.getPassword();
  		
  		long newBno = qnaBoardService.register(dto);
  		
  		redirectAttributes.addFlashAttribute("newBno", newBno);
  		
  		return "redirect:/board/list";
  	}
  	
  	//글 상세 및 수정 폼을 처리하는 read 정의함.
  	@GetMapping({"/read","/modify"})
  	public void read(@ModelAttribute("requestDTO") QnAPageRequestDTO qnaRequestDTO, @RequestParam("bno") Long bno, Model model) {
		QnABoardDTO qnaBoardDTO = qnaBoardService.get(bno);
		var password = qnaBoardDTO.getPassword();
		
		//System.out.println(password);
		model.addAttribute("dto", qnaBoardDTO);
	}
	
	//게시글 수정 삭제 처리 모두 정의하기..
	@PostMapping("/modify")
	public String modify(QnABoardDTO qnaDTO, @ModelAttribute("requestDTO") QnAPageRequestDTO qnaPageRequestDTO, RedirectAttributes redirectAttributes) {
		
		//글 수정시킨 후 read 로 redirect 시킵니다.
		qnaBoardService.modify(qnaDTO);
		
		//read 페이지로 보낼 때, 몇 페이지의 몇번 글인지를 알아야 해서 폼에보면 hidden 으로
		//필수 정보를 설정했음.. 따라서 그 값을 파라미터로 넘김
		
		redirectAttributes.addAttribute("page", qnaPageRequestDTO.getPage());
		redirectAttributes.addAttribute("bno", qnaDTO.getBno());
		
		return "redirect:/board/read";
	}
	
	
	//삭제 처리하기..삭제는 댓글부터 시작해서 부모글을 지웁니다.
	//읽을 내용이 없으니, list로 다시 redirect 시킵니다.
	@PostMapping("/remove")
	public String remove(long bno, RedirectAttributes redirect) {
		
		qnaBoardService.removeWithReplyies(bno);
		
		redirect.addAttribute("newBno", bno);
		
		return "redirect:/board/list";
	}
	@GetMapping("/restT")
	public void restT() {
		
	}
}
