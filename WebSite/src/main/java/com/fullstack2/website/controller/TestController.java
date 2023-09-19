package com.fullstack2.webSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.ReviewRepository;
import com.fullstack2.webSite.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {
	
	@Autowired
	private final ReviewService reviewService;
	
	@Autowired
	private final ReviewRepository reviewRepository;
	
	@GetMapping("/test")
	public void test(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO, Model model) {
		//Model에게 review란에 뿌려질 항목 DTO를 전달함.
		model.addAttribute("result", reviewService.getList(reviewPageRequestDTO));
	}
	
	@PostMapping("/addReview")
	public String addReview(@RequestParam String text, @RequestParam String reviewer) {
		// 엔티티 생성 및 데이터 저장
        Review review = new Review();
        review.setText(text);
        review.setReviewer(reviewer);

        // 리뷰 저장
        reviewRepository.save(review);

        return "redirect:/test"; // 리다이렉트할 페이지 지정
	}
}
