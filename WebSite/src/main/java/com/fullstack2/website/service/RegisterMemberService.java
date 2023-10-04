package com.fullstack2.webSite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.repository.MemberRepository;


@Service
public class RegisterMemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository repository;

    @Autowired
    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public Long join(
	    String email,
	    String password,
	    String name, 
	    String postalCode, 
	    String addressBasic, 
	    String addressRest, 
	    
	    String phoneArea, 
	    String phoneNumber, 
	    String phoneExt, 
	    String mobileArea, 
	    String mobileNumber,
	    String mobileExt,
	    String birthYear, // 수정: 생년
	    String birthMonth,// 수정: 생월
	    String birthDay,
	    String role,
	    String provider
            
	    
	    ) {
	 // 일반전화 및 휴대전화 조합
	    String phone = phoneArea + "-" + phoneNumber + "-" + phoneExt;
	    String  mobile = mobileArea + "-" + mobileNumber + "-" + mobileExt;
	    String birth =birthYear + "-" + birthMonth+ "-" + birthDay;
	
	Member member = Member.createMember(email, password, name, postalCode, addressBasic, addressRest, phone, mobile, birth, passwordEncoder);
        validateDuplicateMember(member);
        repository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
