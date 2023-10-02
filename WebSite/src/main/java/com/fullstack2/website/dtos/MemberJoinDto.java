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
public class MemberJoinDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String postalCode;
    private String addressBasic;
    private String addressRest;
    private String Provider;
    
    
    private String mobile;
    
    private String birth; // 수정: 생년
   
    
    private String role;
}
