package com.fullstack2.webSite.dtos;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class NoticePageResultDTO<DTO, EN> {

	private List<DTO> dtoList;
	
	private int totalPage;
	
	private int page;
	private int size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageList;
	
public NoticePageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		dtoList = result
				.stream()
				.map(fn)
				.collect(Collectors.toList());
		this.totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}

	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
		
		start = tempEnd -9;
		
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	public List<DTO> getDtoList(){
		return this.dtoList;
	}
}
