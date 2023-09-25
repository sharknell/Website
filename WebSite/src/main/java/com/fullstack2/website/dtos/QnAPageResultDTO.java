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
public class QnAPageResultDTO<DTO, EN> {

	private List<DTO> dtoList;
	
	private int totalPage;//총 페이지 카운트
	
	private int page;//현재 페이지
	private int size;//페이지 그룹의 수
	private int start, end;//전체 페이지에서 시작페이지와 마지막페이지
	private boolean prev,next;//이전, 다음을 표시 할 수 있는 변수
	private List<Integer> pageList;//페이지 번호 목록
	
	public QnAPageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
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
		
		int tempEnd = (int)(Math.ceil(page / 5.0)) * 10;
		
		start = tempEnd -4;
		
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	public List<DTO> getDtoList(){
		return this.dtoList;
	}
	
}
