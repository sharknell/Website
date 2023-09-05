package com.fullstack2.website.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDTO {
	private Long rno;
	private String replyer;
	private String text;
	
	private Long bno;//제목글 번호
	private LocalDateTime regDate,modDate;
}
