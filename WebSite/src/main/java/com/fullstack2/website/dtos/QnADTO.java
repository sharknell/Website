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
public class QnADTO {

	private Long qno;
	private String questioner;
	private String text;
	private String password;
	
	private LocalDateTime regDate, modDate;
}
