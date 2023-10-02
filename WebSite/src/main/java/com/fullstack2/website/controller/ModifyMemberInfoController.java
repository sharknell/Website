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
import com.fullstack2.webSite.oauth2.UserProfile;
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
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "mypage";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "mypage";
		}
		return "mypage";
	}

	@GetMapping("/mypage2")
	public String mypage2(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "mypage2";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "mypage2";
		}

		return "mypage2";
	}

	@GetMapping("/manageacc")
	public String manageacc(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "manageacc";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "manageacc";
		}

		return "manageacc";
	}

	@GetMapping("/manageaccSocial")
	public String manageaccSocial(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "manageaccSocial";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "manageaccSocial";
		}

		return "manageaccSocial";
	}

	@GetMapping("/myinfo")
	public String myinfo(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "myinfo";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			System.err.println("dto.getAddress : " + userProfile.getAddressBasic());
			return "myinfo";
		}

		return "myinfo";
	}

	@GetMapping("/managepw")
	public String managepw(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "managepw";
		} 

		return "managepw";
	}

	@PostMapping("/managepw")
	public String changePw(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof MemberJoinDto) {
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
					session.setAttribute("dto", dto);

					// 비밀번호 변경 성공 시 로그아웃 후 메인 페이지로 리다이렉트
					return "redirect:/view/login";
				} else {
					model.addAttribute("dto", dto);
					return "redirect:/info/managepw";
				}
			}
		}
		return "managepw";
	}

	@GetMapping("/managemobile")
	public String managemobile(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "managemobile";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "managemobile";
		}
		return "managemobile";
	}

	@PostMapping("/managemobile")
	public String postManagemobile(@RequestParam("newPhoneNumber") String newPhoneNumber,
			RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {
			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			query.updateMobileByMobile(newPhoneNumber,dto.getEmail());
			dto.setMobile(newPhoneNumber);
			redirectAttributes.addAttribute("message", "변경완료");
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "redirect:/info/mypage";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			query.updateMobileByMobile(newPhoneNumber,userProfile.getEmail());
			userProfile.setMobile(newPhoneNumber);
			model.addAttribute("dto", userProfile);
			redirectAttributes.addAttribute("message", "변경완료");
			session.setAttribute("dto", userProfile);
			return "redirect:/info/mypage2";
		} else {

			return "redirect:/info/managemobile";
		}
	}

	@GetMapping("/checkAddress")
	public String checkAddress(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "checkAddress";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "checkAddress";
		}
		return "checkAddress";
	}

	@PostMapping("/checkAddress")
	public String PostcheckAddress(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "redirect:/info/manageaddress";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "redirect:/info/manageaddress";
		}
		return "redirect:/info/manageaddress";
	}

	@GetMapping("/manageaddress")
	public String manageaddress(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "manageaddress";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "manageaddress";
		}
		return "manageaddress";
	}

	@PostMapping("/manageaddress")
	public String postManageaddress(@RequestParam("newPostalCode") String newPostalCode,
			@RequestParam("newAddressBasic") String newAddressBasic,
			@RequestParam("newAddressRest") String newAddressRest,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			query.updatepostalCode(newPostalCode,dto.getEmail());
			query.updateAddressBasic(newAddressBasic,dto.getEmail());
			query.updateAddressRest(newAddressRest,dto.getEmail());
			dto.setPostalCode(newPostalCode);
			dto.setAddressBasic(newAddressBasic);
			dto.setAddressRest(newAddressRest);

			redirectAttributes.addAttribute("message", "completeUpdateAd");
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "redirect:/info/checkAddress";
		}else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			query.updatepostalCode(newPostalCode,userProfile.getEmail());
			query.updateAddressBasic(newAddressBasic,userProfile.getEmail());
			query.updateAddressRest(newAddressRest,userProfile.getEmail());
			userProfile.setPostalCode(newPostalCode);
			userProfile.setAddressBasic(newAddressBasic);
			userProfile.setAddressRest(newAddressRest);

			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
		}
		return "redirect:/info/checkAddress";
	}
		
		
	


	@GetMapping("/withdraw")
	public String withdraw(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof MemberJoinDto) {

			MemberJoinDto dto = (MemberJoinDto) dtoObject;
			model.addAttribute("dto", dto);
			return "withdraw";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			return "withdraw";
		}
		return "withdraw";
	}

	@PostMapping("/withdraw")
	public String postWithdraw(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberJoinDto dto = (MemberJoinDto) session.getAttribute("dto");
		if (passwordEncoder.matches(password, dto.getPassword())) {
			query.remove(dto.getPassword(), dto.getEmail());
			model.addAttribute("dto", dto);
			redirectAttributes.addAttribute("message", "탈퇴완료");
			return "redirect:/my/main";
		} else if (!passwordEncoder.matches(password, dto.getPassword())) {
			redirectAttributes.addAttribute("message", "탈퇴실패");
			return "redirect:/info/withdraw";
		}
		return "withdraw";

	}
}
