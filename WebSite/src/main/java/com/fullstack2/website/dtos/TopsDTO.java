package com.fullstack2.website.dtos;



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

public class TopsDTO {
	private Long prodnum;//품번
    private String name;//상품이름
    private Character kind;//상품종류
    private Double price;//원가
    private Double disprice;//할인가
    private String content;//상품설명
    private String image;//사진
    private Character useyn;//판매 유뮤, 기본값 y, y = 판매, n = 판매중단
    private String size;//사이즈
}
