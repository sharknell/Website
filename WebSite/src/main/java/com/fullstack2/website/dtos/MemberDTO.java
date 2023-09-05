package com.fullstack2.website.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {
	private String id;
	private String pw;
	private String phoneNum;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private String name;
	private String email;
	private String addr;//집주소
	private String landline;//일반전화
	private String birth;//생년월일
}
