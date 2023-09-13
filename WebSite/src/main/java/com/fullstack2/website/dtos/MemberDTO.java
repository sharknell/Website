package com.fullstack2.webSite.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
    private Long id;
    private String email;
    private String password;
    private String name;
    private String postalCode;
    private String addressBasic;
    private String addressRest;
    private String type;
    private String phoneArea;
    private String phoneNumber;
    private String phoneExt;
    private String mobileArea;
    private String mobileNumber;
    private String mobileExt;
    private String birthYear; // 수정: 생년
    private String birthMonth; // 수정: 생월
    private String birthDay; // 수정: 생일
    
    private String role;
}
