package com.fullstack2.webSite.findPw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack2.webSite.dtos.MemberDTO;
import com.fullstack2.webSite.repository.MemberQuery;




@Controller
public class FindEmailController {
    @Autowired
    private MemberQuery query;
    private MemberDTO dto = new MemberDTO();
    
    @GetMapping("/findEmail")
    public String findEmail() {
        return "findEmail";
    }

    @PostMapping("/findEmail")
    public String findEmailPost(@RequestParam("mobile") String mobile, Model model,
            RedirectAttributes redirectAttributes) {
        String ph = query.selectMobile(mobile);
        if (ph == null) {
            System.err.println("err");
            return "findEmail";
        } else {
            String email = query.selectMobilebyEmail(ph);
            dto.setEmail(email);
            model.addAttribute("dto", dto);
            redirectAttributes.addFlashAttribute("message", "err");
            redirectAttributes.addFlashAttribute("dto", dto);
            System.err.println("성공");
            return "resultFindEmail";
        }
    }

    @GetMapping("/resultFindEmail")
    public String resultFindEmail() {
        return "resultFindEmail";
    }
}
