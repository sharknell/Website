package com.fullstack2.webSite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack2.webSite.dtos.MemberDTO;
import com.fullstack2.webSite.service.RegisterMemberService;




@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    
    private final RegisterMemberService registerMemberService;

    public AuthorizationController(RegisterMemberService registerMemberService) {
        this.registerMemberService = registerMemberService;
    }

 // 회원가입 시 목록 추가할 곳
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberDTO dto) {
	 try {
	            // 여기서 MemberDTO에 있는 phone과 mobile 값을 추출해서 사용
	            
	            registerMemberService.join(
	                dto.getEmail(), 
	                dto.getPassword(), 
	                dto.getName(), 
	                dto.getPostalCode(), 
	                dto.getAddressBasic(), 
	                dto.getAddressRest(), 
	                 
	                dto.getPhoneArea(),
	                dto.getPhoneNumber(),
	                dto.getPhoneExt(), // 여기서 조합한 phone 값을 전달
	                dto.getMobileArea(),
	                dto.getMobileNumber(),
	                dto.getMobileExt(),
	                 // 여기서 조합한 mobile 값을 전달
	                dto.getBirthYear(), 
	                dto.getBirthMonth(),
	                dto.getBirthDay(),
	                
	                dto.getRole(),
	                dto.getProvider()
	               
	            );
	            
	            return ResponseEntity.ok("가입 성공");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
    }
}


