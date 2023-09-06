package com.fullstack2.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String home() {
        return "home"; // 홈 페이지
    }

    @GetMapping("/main")
    public String main() {
        return "main"; // 메인 페이지
    }

    @GetMapping("/aboutus")
    public String aboutUs() {
        // About Us 페이지 로직 처리
        return "aboutUs"; // AboutUs.html 페이지로 이동
    }

    @GetMapping("/onlinestore")
    public String onlineStore() {
        // Online Store 페이지 로직 처리
        return "onlinestore"; // online-store.html 페이지로 이동
    }

    @GetMapping("/collection")
    public String collection() {
        // Collection 페이지 로직 처리
        return "collection"; // collection.html 페이지로 이동
    }

    @GetMapping("/community")
    public String community() {
        // Community 페이지 로직 처리
        return "community"; // community.html 페이지로 이동
    }
    
    @GetMapping("/newarrivals")
    public String newArrivals() {
        // NEW ARRIVALS 서브메뉴 페이지 로직 처리
        return "newarrivals"; // newarrivals.html 페이지로 이동
    }
}
