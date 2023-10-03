package com.fullstack2.webSite.controller;


import com.fullstack2.webSite.dtos.MemberJoinDto;
import com.fullstack2.webSite.entity.Cart;
import com.fullstack2.webSite.oauth2.UserProfile;
import com.fullstack2.webSite.repository.CartRepository;
import com.fullstack2.webSite.repository.MemberQuery;
import com.fullstack2.webSite.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class CartController {

    private final MemberService memberService;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    MemberQuery query;

    @GetMapping("/cart")
    public String cartView(@AuthenticationPrincipal User user, Model model, HttpSession session) {
        Object dtoObject = session.getAttribute("dto");
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;

            List<Cart> carts = cartRepository.findAllByMember(dto.getId());
            model.addAttribute("dto", dto);
            model.addAttribute("list", carts);
            session.setAttribute("dto", dto);
            return "cart";
        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;

            List<Cart> carts = cartRepository.findAllByMember(userProfile.getId());
            model.addAttribute("dto", userProfile);
            model.addAttribute("list", carts);
            session.setAttribute("dto", userProfile);

            return "cart";
        }
        return "cart";

    }

    @PostMapping("/cart")
    public String Cart(Cart cart, @AuthenticationPrincipal User user, HttpSession session) {
        Object dtoObject = session.getAttribute("dto");
        System.out.println(cart.getProduct().getPrice());
        if (dtoObject instanceof MemberJoinDto) {
            MemberJoinDto dto = (MemberJoinDto) dtoObject;
            cart.setMember(dto.getId());
            cartRepository.save(cart);
            return "redirect:/my/cart";

        } else if (dtoObject instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObject;

            cart.setMember(userProfile.getId());
            System.err.println(cart.getMember());
            cartRepository.save(cart);
            return "redirect:/my/cart";

        }

        return "redirect:/my/cart";

    }

}
