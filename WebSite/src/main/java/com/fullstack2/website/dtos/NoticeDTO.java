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
public class NoticeDTO {

	private Long nno;
	private String admin;
	private String title;
	private String content;
	
	private LocalDateTime regDate, modDate;
}
