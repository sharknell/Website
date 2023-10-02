package com.fullstack2.webSite.findPw;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor

public class MailController {
    private final UserService userService;
    private final SendEmailService sendEmailService;

    // Email과 name의 일치여부를 check하는 컨트롤러
    @GetMapping("/findPw")
    public String findPw() {

	return "findPw";
    }

    @PostMapping("/findPw")
    public String findPwPost(@RequestParam("userEmail") String email, @RequestParam("userName") String name,
	    RedirectAttributes attributes) {
	boolean pwFindCheck = userService.userEmailCheck(email, name);
	if (pwFindCheck) {
	    MailDTO dto = sendEmailService.createMailAndChangePassword(email, name);
	    sendEmailService.mailSend(dto);
	    attributes.addAttribute("message", "이메일전송");

	    return "redirect:/view/login";
	} else {
	    attributes.addAttribute("message", "이메일전송");
	    return "redirect:/findPw";
	}

	// }

	/*
	 * @GetMapping("/check/findPw") public Map<String, Boolean>
	 * pw_find(@RequestParam("userEmail") String email,@RequestParam("userName")
	 * String name){ Map<String,Boolean> json = new HashMap<>();
	 * 
	 * 
	 * 
	 * boolean pwFindCheck = userService.userEmailCheck(email,name);
	 * 
	 * System.out.println(pwFindCheck); json.put("check", pwFindCheck); return json;
	 * }
	 */

	// 등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
	/*
	 * @PostMapping("/check/findPw/sendEmail") public void
	 * sendEmail(@RequestParam("userEmail") String
	 * userEmail,@RequestParam("userName") String userName){ MailDTO dto =
	 * sendEmailService.createMailAndChangePassword(userEmail, userName);
	 * sendEmailService.mailSend(dto);
	 * 
	 * }
	 */
    }
}