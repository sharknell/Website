package com.fullstack2.webSite.oauth2;



import com.fullstack2.webSite.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserProfile {

    private String name; // 사용자 이름
    private String provider; // 로그인한 서비스
    private String email; // 사용자의 이메일
    // 사용자의 실제 이름
    private String birth; 
    private String birthDay; 
    private String birthYear; 
    private String mobile; 
    
    private String postalCode; 
    private String addressBasic; 
    private String addressRest; 
    private String role; 
   

    
    public void setRole(String role) {
		this.role = role;
	}
    public void setAddressBasic(String addressBasic) {
	this.addressBasic = addressBasic;
    }
    public void setAddressRest(String addressRest) {
	this.addressRest = addressRest;
    }
    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }
    public void setBirth(String birth) {
	this.birth = birth;
    }
    public void setMobile(String mobile) {
	this.mobile = mobile;
    }
    public void setUserName(String userName) {
        this.name = userName;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setBirthDay(String birthDay) {
	this.birthDay = birthDay;
    }
    public void setBirthYear(String birthYear) {
	this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    // DTO 파일을 통하여 Entity를 생성하는 메소드
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .birth(this.birth)
                .mobile(this.mobile)
                .provider(this.provider)
                .role("USER")
                .build();
    }
}