package com.fullstack2.webSite.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDTO {

	private Long rno;
	private String reviewer;
	private String text;
	private String password;
	
	private LocalDateTime regDate, modDate;
}
