package com.fullstack2.webSite.dtos;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class NoticePageRequestDTO {

	private int page;
	private int size;
	
	public NoticePageRequestDTO() {
		this.page = 1;
		this.size = 20;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page - 1, size, sort);
	}
}
