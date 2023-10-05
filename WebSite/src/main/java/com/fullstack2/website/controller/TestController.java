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

import com.fullstack2.webSite.dtos.NoticePageRequestDTO;
import com.fullstack2.webSite.dtos.QnADTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.dtos.ReviewDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageResultDTO;
import com.fullstack2.webSite.entity.Notice;
import com.fullstack2.webSite.entity.Product;
import com.fullstack2.webSite.entity.QnA;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.NoticeRepository;
import com.fullstack2.webSite.repository.QnARepository;
import com.fullstack2.webSite.repository.ReviewRepository;
import com.fullstack2.webSite.service.Product_Service;
import com.fullstack2.webSite.service.QnAService;
import com.fullstack2.webSite.service.ReviewService;

@Controller
public class TestController {
	
	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;
	private final QnAService qnaService;
	private final QnARepository qnaRepository;
	private final Product_Service productService;
	private final NoticeRepository noticeRepository;
	
	@Autowired // 생성자에 대한 자동 주입 어노테이션
	public TestController(
			ReviewService reviewService,
			ReviewRepository reviewRepository,
			QnAService qnaService,
			QnARepository qnaRepository,
			Product_Service productService,
			NoticeRepository noticeRepository) {
		
		this.reviewService = reviewService;
		this.reviewRepository = reviewRepository;
		this.qnaService = qnaService;
		this.qnaRepository = qnaRepository;
		this.productService = productService;
		this.noticeRepository = noticeRepository;
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
	public String addReview(@RequestParam String text, 
	                       @RequestParam Long itemcount,
	                       @RequestParam String content,// 이 부분 추가
	                       @ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO
	                       ) {
	    // 현재 로그인한 사용자의 정보를 가져옵니다.
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // 인증 정보에서 사용자 이름을 가져옵니다.
	    String reviewer = authentication.getName();
	    
	    // 엔티티 생성 및 데이터 저장
	    Review review = new Review();
	    review.setText(text);
	    review.setReviewer(reviewer);
	    review.setContent(content);
	    review.setModDate(LocalDateTime.now());
	    review.setRegDate(LocalDateTime.now());
	    
	    // 상품의 itemcount 값을 설정
	    Product product = new Product();
	    product.setItemcount(itemcount);
	    review.setProduct(product);

	    // 리뷰 저장
	    reviewRepository.save(review);

	    return "redirect:/product/outer";
	}
	@PostMapping("/addQnA")
	public String addQnA(@RequestParam String text, 
						 @RequestParam String content,
					     @ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO) {
		
		//현재 로그인 한 사용자의 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//인증 정보에서 사용자 이름을 가져옴
		String questioner = authentication.getName();
		
		//엔티티 생성 및 데이터 저장
		QnA qna = new QnA();
		qna.setText(text);
		qna.setQuestioner(questioner);
		qna.setContent(content);
		qna.setModDate(LocalDateTime.now());
		qna.setRegDate(LocalDateTime.now());
		
		qnaRepository.save(qna);

	    
		return "redirect:/my/qna";
	}
	
	@PostMapping("/addNotice")
	public String addNotice(@RequestParam String title,
							@RequestParam String content,
							@ModelAttribute("noticePageRequestDTO") NoticePageRequestDTO noticePageRequestDTO) {
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setModDate(LocalDateTime.now());
		notice.setRegDate(LocalDateTime.now());
		
		noticeRepository.save(notice);
		
		return "redirect:/my/noticemanage";
	}
}
