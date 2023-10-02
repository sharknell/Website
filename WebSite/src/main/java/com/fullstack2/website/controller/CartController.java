package com.fullstack2.webSite.controller;

import com.fullstack2.webSite.config.MyUserDetailService;
import com.fullstack2.webSite.config.UserAuthorize;
import com.fullstack2.webSite.entity.Cart;
import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.repository.CartRepository;
import com.fullstack2.webSite.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final MemberService memberService;
    private final CartRepository cartRepository;

    @GetMapping("/cart")
    public String cartView(@AuthenticationPrincipal User user, Model model){
        Member member = memberService.findOne(user.getUsername()).get();
        List<Cart> carts = cartRepository.findAllByMember(member.getId());
        System.out.println(carts.get(0).getSize());
        model.addAttribute("list",carts);
        model.addAttribute("username", member.getName());
        return "/cart";
    }

        @PostMapping("/cart")
        public String Cart(Cart cart, @AuthenticationPrincipal User user){
            System.out.println(cart.getProduct().getPrice());
            cart.setMember(memberService.findOne(user.getUsername()).get().getId());
            System.out.println(cart.getMember());

            cartRepository.save(cart);
            return "redirect:/cart";
        }
}
