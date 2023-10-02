package com.fullstack2.webSite.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import com.fullstack2.webSite.oauth2.OAuth2Service;
import com.fullstack2.webSite.oauth2.UserProfile;
import com.nimbusds.oauth2.sdk.token.AccessToken;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private MemberQuery query;
    @Autowired
    private OAuth2Service oAuth2Service; // OAuth2Service 주입 추가
    MemberJoinDto dto = new MemberJoinDto();
    
    Member member = new Member();
    MemberService memberService;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/join")
    public String joinPage() {
	return "join";
    }

    @GetMapping("/admin")
    public String admin() {
	return "admin";
    }

    @GetMapping("/login")
    public String login() {
	return "login";
    }

    @GetMapping("/mainLog")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model, HttpSession session) {
	if (user != null) {
	    String email = user.getUsername();

	    dto.setEmail(email);
	    dto.setPassword(query.selectPw(email));
	    dto.setName(query.selectName(email));
	    dto.setProvider(query.selectProvider(email));
	    // 주소
	    dto.setPostalCode(query.selectpostalCode(email));
	    dto.setAddressBasic(query.selectaddressBasic(email));
	    dto.setAddressRest(query.selectaddressRest(email));
	    // 핸드폰 번호
	    dto.setMobile(query.selectPh(email));
	    // 생일
	    dto.setBirth(query.selectBirth(email));
	    // 권한
	    dto.setRole(query.selectRole(email));

	    System.out.println("mainLog dto:" + dto);
	    session.setAttribute("dto", dto);
	    model.addAttribute("dto", dto);
	    return "mainLog";
	} 
	
	return "mainLog";

    }
    @GetMapping("/mainLog2")
    public String dashboardPage2(@AuthenticationPrincipal User user, Model model, HttpSession session) {
    	if (user == null) {
    		
    		UserProfile userProfile = (UserProfile) session.getAttribute("dto");
    	    userProfile.setPostalCode(query.selectpostalCode(userProfile.getEmail()));
    	    userProfile.setAddressBasic(query.selectaddressBasic(userProfile.getEmail()));
    	    userProfile.setAddressRest(query.selectaddressRest(userProfile.getEmail()));
    	    userProfile.setProvider(query.selectProvider(userProfile.getEmail()));
    	    // 핸드폰 번호
    	    userProfile.setMobile(query.selectPh(userProfile.getEmail()));
    	    // 생일
    	    userProfile.setBirth(query.selectBirth(userProfile.getEmail()));
    	    // 권한
    	    userProfile.setRole(query.selectRole(userProfile.getEmail()));
    	    System.err.println("mainLog dto:" + userProfile.getAddressBasic());
    	    System.err.println("mainLog dto:" + userProfile.getEmail());
    	    session.setAttribute("dto", userProfile);
    	    model.addAttribute("dto", userProfile);
    	}
    	return "mainLog2";
    	
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