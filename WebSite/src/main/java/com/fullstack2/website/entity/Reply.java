package com.fullstack2.website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@ToString(exclude = "member")//컴파일에러 없애려고 임시로 선언
public class Reply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String text;
	private String replyer;
	
	//참조되는 부모의 글 번호를 ManyToOne 으로 설정함
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;//컴파일에러 없애려고 임시로 선언
	
	
	public void changeText(String text) {
		this.text = text;
	}
}
