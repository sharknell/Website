package com.fullstack2.webSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack2.webSite.dtos.MemberJoinDto;
import com.fullstack2.webSite.repository.MemberQuery;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/info")
public class ModifyMemberInfoController {
    private MemberQuery query;
    private PasswordEncoder passwordEncoder;

    @Autowired // passwordEncoder 주입
    public ModifyMemberInfoController(MemberQuery query, PasswordEncoder passwordEncoder) {
        this.query = query;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "mypage";
    }

    @GetMapping("/manageacc")
    public String manageacc(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "manageacc";
    }

    @GetMapping("/myinfo")
    public String myinfo(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "myinfo";
    }

    @GetMapping("/managepw")
    public String managepw(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "managepw";
    }

    @PostMapping("/managepw")
    public String changePw(
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model,
            HttpSession session
            , RedirectAttributes redirectAttributes
            ) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");

        // 현재 비밀번호와 저장된 비밀번호를 비교하여 일치하는지 확인
        if (passwordEncoder.matches(password, dto.getPassword())) {
            // 새 비밀번호와 확인 비밀번호가 일치하는지 확인
            if (newPassword.equals(confirmPassword)) {
                // 새 비밀번호를 암호화하여 저장
                String hashedNewPassword = passwordEncoder.encode(newPassword);
                query.updatePassword(hashedNewPassword, dto.getPassword());
                dto.setPassword(hashedNewPassword);
                model.addAttribute("dto", dto);
                redirectAttributes.addAttribute("message", "비밀번호변경완료");

                // 비밀번호 변경 성공 시 로그아웃 후 메인 페이지로 리다이렉트
                return "redirect:/view/login";
            }
        }
        
        // 비밀번호 변경 실패 시 변경 페이지로 다시 이동
        model.addAttribute("dto", dto);
        return "redirect:/info/managepw";
    }

    @GetMapping("/managemobile")
    public String managemobile(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "managemobile";
    }
    
    
    @PostMapping("/managemobile")
    public String postManagemobile(
            @RequestParam("password") String password,
            @RequestParam("newPhoneNumber") String newPhoneNumber,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpSession session
            ) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");

        // 현재 비밀번호와 저장된 비밀번호를 비교하여 일치하는지 확인
        if (passwordEncoder.matches(password, dto.getPassword())) {
            // 새 비밀번호와 확인 비밀번호가 일치하는지 확인
            query.updateMobile(newPhoneNumber, dto.getPassword());
                // 새 비밀번호를 암호화하여 저장
                dto.setMobile(newPhoneNumber);
                model.addAttribute("dto", dto);
                redirectAttributes.addAttribute("message", "변경완료");
                return "redirect:/info/mypage";
            }
        model.addAttribute("dto", dto);
        redirectAttributes.addAttribute("message", "변경실패");
        return "redirect:/info/managemobile";
    }
    

    @GetMapping("/checkAddress")
    public String checkAddress(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "checkAddress";
    }
    @PostMapping("/checkAddress")
    public String PostcheckAddress(Model model, HttpSession session) {
	MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
	model.addAttribute("dto", dto);
	return "redirect:/info/manageaddress";
    }
    
    @GetMapping("/manageaddress")
    public String manageaddress(Model model, HttpSession session) {
        MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
        model.addAttribute("dto", dto);
        return "manageaddress";
    }
    
    @PostMapping("/manageaddress")
    public String postManageaddress(
	    @RequestParam("newPostalCode") String newPostalCode,
            @RequestParam("newAddressBasic") String newAddressBasic,
            @RequestParam("newAddressRest") String newAddressRest,
            @RequestParam("password") String password,
	    Model model, HttpSession session,
	    RedirectAttributes redirectAttributes) {
	 MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
	if (passwordEncoder.matches(password, dto.getPassword())) {
	    query.updatepostalCode(newPostalCode, dto.getPassword());
	    query.updateAddressBasic(newAddressBasic, dto.getPassword());
	    query.updateAddressRest(newAddressRest, dto.getPassword());
	    dto.setPostalCode(newPostalCode);
	    dto.setAddressBasic(newAddressBasic);
	    dto.setAddressRest(newAddressRest);
	    
	    redirectAttributes.addAttribute("message","completeUpdateAd");
	    return "redirect:/info/checkAddress";
	}
	model.addAttribute("dto",dto);
	redirectAttributes.addAttribute("message","fail");
        return "redirect:/info/checkAddress";
    }
    @GetMapping("/withdraw")
    public String withdraw(Model model, HttpSession session) {
	 MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
	 model.addAttribute("dto", dto);
	return "withdraw";
    }
    @PostMapping("/withdraw")
    public String postWithdraw(
	    @RequestParam("email") String email,
	    @RequestParam("password") String password,
	    Model model, HttpSession session,
	    RedirectAttributes redirectAttributes) {
	MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
	if (passwordEncoder.matches(password, dto.getPassword())) {
	    query.remove(dto.getPassword(), dto.getEmail());
	    model.addAttribute("dto",dto);
	    redirectAttributes.addAttribute("message", "탈퇴완료");
	    return "redirect:/my/main";
	}else if (!passwordEncoder.matches(password, dto.getPassword())) {
	    redirectAttributes.addAttribute("message","탈퇴실패");
	    return "redirect:/info/withdraw";
	}
	return "withdraw";
	
	
    }
}
