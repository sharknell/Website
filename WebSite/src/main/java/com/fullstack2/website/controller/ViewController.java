package com.fullstack2.webSite.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack2.webSite.config.AdminAuthorize;
import com.fullstack2.webSite.config.UserAuthorize;
import com.fullstack2.webSite.dtos.MemberJoinDto;
import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.repository.MemberQuery;
import com.fullstack2.webSite.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private MemberQuery query;
	MemberJoinDto dto = new MemberJoinDto();
	Member member = new Member();
	MemberService memberService;
	 @Autowired
	    private PasswordEncoder encoder;

	@GetMapping("/login")
	public String loginPage() {
		
		return "login";
	}

	@GetMapping("/join")
	public String joinPage() {
		return "join";
	}

	
	
	//여기서 유저 정보 모두 끌어오기
	@GetMapping("/mainLog")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model,HttpSession session) {
		String email = user.getUsername();
		
		dto.setEmail(email);
		dto.setPassword(query.selectPw(email));
		dto.setName(query.selectName(email));
		
		//주소
		dto.setPostalCode(query.selectpostalCode(email));
		dto.setAddressBasic(query.selectaddressBasic(email));
		dto.setAddressRest(query.selectaddressRest(email));
		//핸드폰 번호
		dto.setMobile(query.selectPh(email));
		//생일
		dto.setBirth(query.selectBirth(email));
		// 권한
		dto.setRole(query.selectRole(email));
		
		System.out.println("mainLog dto:"+ dto);
		session.setAttribute("dto", dto);
		model.addAttribute("dto", dto);
		return "mainLog";
	}

	@GetMapping("/setting/admin")
	@AdminAuthorize
	public String adminSettingPage() {
		return "admin_setting";
	}

	@GetMapping("/setting/user")
	@UserAuthorize
	public String userSettingPage() {
		return "user_setting";
	}
}