package com.fullstack2.webSite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import org.springframework.security.crypto.password.PasswordEncoder;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) 
    private String email;
    private String password;
    private String name;
    // 주소
   
    private String postalCode;
    private String addressBasic;
    private String addressRest;
  
    private String phone;
    private String mobile;
    private String birth;
    private String role;
    
    
 // 회원가입 시 목록 추가할 곳
    public static Member createUser(String email, String password, String name, String postalCode, String addressBasic, String addressRest, String phone, String mobile, String birth, PasswordEncoder passwordEncoder) {
        return new Member(null, email, passwordEncoder.encode(password), name, postalCode, addressBasic, addressRest, phone, mobile, birth,"USER" );
    }

   
}
