package com.fullstack2.webSite.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullstack2.webSite.dtos.QnADTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.dtos.ReviewDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageResultDTO;
import com.fullstack2.webSite.entity.QnA;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.QnARepository;
import com.fullstack2.webSite.repository.ReviewRepository;
import com.fullstack2.webSite.service.QnAService;
import com.fullstack2.webSite.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
public class TestController {
	
	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;
	private final QnAService qnaService;
	private final QnARepository qnaRepository;
	
	@Autowired // 생성자에 대한 자동 주입 어노테이션
	public TestController(
			ReviewService reviewService,
			ReviewRepository reviewRepository,
			QnAService qnaService,
			QnARepository qnaRepository) {
		this.reviewService = reviewService;
		this.reviewRepository = reviewRepository;
		this.qnaService = qnaService;
		this.qnaRepository = qnaRepository;
	}
	
	@GetMapping("/test")
	public void test(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO,
	                 @ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO,
	                 Model model) {
	    // Review 정보와 QnA 정보를 각각 사용하여 작업 수행
	    ReviewPageResultDTO<ReviewDTO, Review> reviewResult = reviewService.getList(reviewPageRequestDTO);
	    QnAPageResultDTO<QnADTO, QnA> qnaResult = qnaService.getList(qnaPageRequestDTO);
	    
	    // 모델에 결과 추가
	    model.addAttribute("reviewResult", reviewResult);
	    model.addAttribute("qnaResult", qnaResult);
	}

    @PostMapping("/addReview")
    public String addReview(@RequestParam String text, @ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO) {
        // 현재 로그인한 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보에서 사용자 이름을 가져옵니다.
        String reviewer = authentication.getName();

        // 엔티티 생성 및 데이터 저장
        Review review = new Review();
        review.setText(text);
        review.setReviewer(reviewer);
        review.setModDate(LocalDateTime.now());
        review.setRegDate(LocalDateTime.now());

        // 리뷰 저장
        reviewRepository.save(review);

        // 리뷰를 추가한 후 검색 타입을 다시 설정
        reviewPageRequestDTO.setType("리뷰");

        return "redirect:/test"; // 리다이렉트할 페이지 지정
    }

	@PostMapping("/addQnA")
	public String addQnA(@RequestParam String text, @ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO) {
		//현재 로그인 한 사용자의 정보를 가져옴
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//인증 정보에서 사용자 이름을 가져옴
		String questioner = authentication.getName();
		
		//엔티티 생성 및 데이터 저장
		QnA qna = new QnA();
		qna.setText(text);
		qna.setQuestioner(questioner);
		qna.setModDate(LocalDateTime.now());
		qna.setRegDate(LocalDateTime.now());
		
		qnaRepository.save(qna);
		
		return "redirect:/test";
	}
}
