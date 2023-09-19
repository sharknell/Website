package com.fullstack2.webSite.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.dtos.ReviewDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageResultDTO;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImple implements ReviewService {

	private final ReviewRepository reviewRepository;
	
	@Override
	public Long review(ReviewDTO dto) {
		Review review = dtoToEntity(dto);
		reviewRepository.save(review);
		return review.getRno();
	}


    @Override
    public ReviewPageResultDTO<ReviewDTO, Review> getList(ReviewPageRequestDTO pageRequestDTO) {
        // Page<Review> 타입으로 반환하도록 수정
        Page<Review> result = reviewRepository.getReviewPaged(pageRequestDTO.getPageable(Sort.by("rno").descending()));
        return new ReviewPageResultDTO<>(result, this::entityToDTO);
    }
}
