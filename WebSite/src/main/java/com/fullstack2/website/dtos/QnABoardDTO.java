package com.fullstack2.webSite.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Community 내에 있는 QnA를 게시판처럼 활용하기 위해 담는 DTO 정의.
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnABoardDTO {

	private Long bno;//QnA글 번호
	private String title;//QnA제목
	private String content;//QnA내용부
	private String writerEmail;//작성자 이메일
	private String writerName;//QnA글 작성자 이름
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int replyCount;
	private String password;
}
