package com.fullstack2.webSite.dtos;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
 * 화면에서 사용자가 요청하는 QnA 페이지 번호를 분석해서
 * 그 번호에 해당하는 글 목록 갯수 정보를 담는 DTO
 */
@Builder
@AllArgsConstructor
@Data
public class QnAPageRequestDTO {

	private int page;
	private int size;
	
	private String type;//검색타입
	private String keyword;//검색 키워드
	
	public QnAPageRequestDTO() {
		this.page = 1;
		this.size = 5;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page - 1, size, sort);
	}
}
