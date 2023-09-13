package com.fullstack2.webSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	

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
		return "redirect:/board/list";
	}

	@GetMapping("/newarrivals")
	public String newArrivals() {
		// NEW ARRIVALS 서브메뉴 페이지 로직 처리
		return "newarrivals"; // newarrivals.html 페이지로 이동
	}


	@GetMapping("/orderhistory")
	public String orderhistoryPage() {
		// SNS 페이지 로직 처리
		return "orderhistory"; // manageacc.html 페이지로 이동
	}

	

	@GetMapping("/points")
	public String pointsPage() {
		// SNS 페이지 로직 처리
		return "points"; // manageacc.html 페이지로 이동
	}

	@GetMapping("/manageposts")
	public String managepostscPage() {
		// SNS 페이지 로직 처리
		return "manageposts"; // manageacc.html 페이지로 이동
	}


	
	@GetMapping("/cart2")
	public String cart2Page() {
		// SNS 페이지 로직 처리
		return "cart2"; // manageacc.html 페이지로 이동
	}

}
