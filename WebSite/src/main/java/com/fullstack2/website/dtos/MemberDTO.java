package com.fullstack2.webSite.dtos;

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
}
