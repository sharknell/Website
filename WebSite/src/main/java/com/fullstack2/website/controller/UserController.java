package com.fullstack2.webSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullstack2.webSite.dtos.ReviewDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageResultDTO;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.ReviewRepository;
import com.fullstack2.webSite.service.ReviewService;

@Controller
@RequestMapping("/my")
public class UserController {

	private final ReviewService reviewService;
	
	@Autowired // 생성자에 대한 자동 주입 어노테이션
	public UserController(ReviewService reviewService){
		this.reviewService = reviewService;
	}

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

   @GetMapping("/mypage")
   public String mypage() {
      // 회원 가입 페이지 로직 처리
      return "mypage"; // mypage.html 페이지로 이동
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

   @GetMapping("/manageacc")
   public String manageaccPage() {
      // SNS 페이지 로직 처리
      return "manageacc"; // manageacc.html 페이지로 이동
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

   @GetMapping("/managename")
   public String managenamePage() {
      // SNS 페이지 로직 처리
      return "managename"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/managepw")
   public String managepwPage() {
      // SNS 페이지 로직 처리
      return "managepw"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/findpassword")
   public String testpasswordPage() {
      // SNS 페이지 로직 처리
      return "findpassword"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/findpassword_drag")
   public String testpassword_dragPage() {
      // SNS 페이지 로직 처리
      return "findpassword_drag"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/findemail")
   public String findemailPage() {
      // SNS 페이지 로직 처리
      return "findemail"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/managemobile")
   public String managemobilePage() {
      // SNS 페이지 로직 처리
      return "managemobile"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/manageaddress")
   public String manageaddressPage() {
      // SNS 페이지 로직 처리
      return "manageaddress"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/withdraw")
   public String withdrawPage() {
      // SNS 페이지 로직 처리
      return "withdraw"; // manageacc.html 페이지로 이동
   }

   @GetMapping("/cart2")
   public String cart2Page() {
      // SNS 페이지 로직 처리
      return "cart2"; // manageacc.html 페이지로 이동
   }
   @GetMapping("/outer")
   public String outerPage() {
      // SNS 페이지 로직 처리
      return "outer"; // manageacc.html 페이지로 이동
   }
   
   @GetMapping("/outer/outer1")
   public String outer1Page() {
      // SNS 페이지 로직 처리
      return "/outer/outer1"; // manageacc.html 페이지로 이동
   }
   
   @GetMapping("/notice")
   public String notice() {
	   return "notice";
   }
   
   @GetMapping("/review")
   public String review(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO, Model model) {

	   ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);
	   
	   model.addAttribute("reviewResult", reviewResult);
	   
	   return "review";
   }

}