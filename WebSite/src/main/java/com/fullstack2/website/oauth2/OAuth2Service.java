package com.fullstack2.webSite.oauth2;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.entity.Member;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    private final UserRepository userRepository;
    @Autowired
    HttpSession session;
  

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2 사용자 정보를 가져오는 코드
        OAuth2UserService<OAuth2UserRequest, ?> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 로그인을 수행한 서비스의 이름

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); // PK가 되는 정보

        Map<String, Object> attributes = oAuth2User.getAttributes(); // 사용자가 가지고 있는 정보

        // 사용자 정보를 UserProfile 객체로 변환
        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
        userProfile.setProvider(registrationId);
        if (registrationId.equals("kakao") ||registrationId.equals("naver")) {
        	updateOrSaveUser(userProfile);
	}else {
	    updateOrSaveUserGoogle(userProfile);
	}
        // 사용자 정보를 업데이트 또는 저장
        

        // 사용자 정보를 커스텀 속성에 설정하여 반환
        Map<String, Object> customAttribute =
                getCustomAttribute(registrationId, userNameAttributeName, attributes, userProfile);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                customAttribute,
                userNameAttributeName);
    }

    public Map<String, Object> getCustomAttribute(String registrationId,
                                  String userNameAttributeName,
                                  Map<String, Object> attributes,
                                  UserProfile userProfile) {
        Map<String, Object> customAttribute = new ConcurrentHashMap<>();
        
        //여기 추가
        if (registrationId.equals("kakao") || registrationId.equals("naver") ) {
            customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
            customAttribute.put("provider", registrationId);
            customAttribute.put("name", userProfile.getName());
            customAttribute.put("email", userProfile.getEmail());
            customAttribute.put("birth", userProfile.getBirth());
            customAttribute.put("mobile", userProfile.getMobile());
            
            userProfile.setEmail((String) customAttribute.get("email"));
            userProfile.setName(userProfile.getName());
            userProfile.setBirth(userProfile.getBirth());
            userProfile.setMobile(userProfile.getMobile());
            session.setAttribute("dto", userProfile);
            System.err.println(userProfile.getEmail()); 
            

            return customAttribute;
	}else if (registrationId.equals("google")) {
	    customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
            customAttribute.put("provider", registrationId);
            customAttribute.put("name", userProfile.getName());
            customAttribute.put("email", userProfile.getEmail());
            
            
            userProfile.setEmail((String) customAttribute.get("email"));
            userProfile.setName(userProfile.getName());
            
            session.setAttribute("dto", userProfile);
            System.err.println(userProfile.getEmail()); 
            

            return customAttribute;
	}
	return customAttribute;
	    
	
       
    }

    public Member updateOrSaveUser(UserProfile userProfile) {
	Member member = userRepository
                .findUserByEmailAndProvider(userProfile.getEmail(), userProfile.getProvider())
                .map(value -> value.updateUser(userProfile.getName(), userProfile.getEmail(),userProfile.getBirth(),userProfile.getMobile()))
                .orElse(userProfile.toEntity());

        return userRepository.save(member);
    }
    public Member updateOrSaveUserGoogle(UserProfile userProfile) {
   	Member member = userRepository
                   .findUserByEmailAndProvider(userProfile.getEmail(), userProfile.getProvider())
                   .map(value -> value.updateUserGoogle(userProfile.getName(), userProfile.getEmail()))
                   .orElse(userProfile.toEntity());

           return userRepository.save(member);
       }
}