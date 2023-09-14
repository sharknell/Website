package com.fullstack2.webSite.findPw;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.repository.MemberQuery;
import com.fullstack2.webSite.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberQuery query;
    private final MemberRepository memberRepository;

    public boolean userEmailCheck(String email, String userName) {
	String findEmail = query.selectEmail(email);
	String findName = query.selectNameByName(userName);
	System.out.println(findEmail);
	System.out.println(findName);

	if (findEmail != null && findName != null) {
	    return true;
	} else {
	    return false;
	}
    }
}