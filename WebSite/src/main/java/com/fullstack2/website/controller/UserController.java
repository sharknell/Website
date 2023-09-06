package com.fullstack2.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        // 로그인 페이지 로직 처리
        return "login"; // login.html 페이지로 이동
    }

    @GetMapping("/join")
    public String joinPage() {
        // 회원 가입 페이지 로직 처리
        return "join"; // join.html 페이지로 이동
    }

    @GetMapping("/cart")
    public String cartPage() {
        // 장바구니 페이지 로직 처리
        return "cart"; // cart.html 페이지로 이동
    }

    @GetMapping("/sns")
    public String snsPage() {
        // SNS 페이지 로직 처리
        return "sns"; // sns.html 페이지로 이동
    }
}
