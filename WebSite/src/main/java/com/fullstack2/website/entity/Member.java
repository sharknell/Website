package com.fullstack2.website.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 45, nullable = false)
    private Long id;//회원 번호, 이걸로 로그인하는게 아니라 자동 생성됨
    
    @Column(length = 45, nullable = false)
    private String pw;
    
    @Column(length = 45, nullable = false)
    private String name;
    
    @Column(length = 45, nullable = false, unique = true)
    private String email;//로그인은 이걸로

    @Column(length = 45, nullable = false)
    private String phoneNum;
    
    @Column(length = 45, nullable = false)
    private String addr;//집주소
    
    @Column(length = 45)
    private String landline;//일반전화
    
    @Column(length = 45, nullable = false)
    private String birth;//생년월일
    
    
    
    
    
}
