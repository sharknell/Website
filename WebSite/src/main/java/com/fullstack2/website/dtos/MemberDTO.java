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
	private Long id;//회원 번호, 이걸로 로그인하는게 아니라 자동 생성됨
	private String pw;
	private String phoneNum;//핸드폰
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private String userName;
	private String email;//로그인은 이걸로
	private String addrBasic;//집주소
	private String addrCode;//집주소
	private String addrRest;//집주소
	private String landline;//일반전화
	private String birth;//생년월일
}
