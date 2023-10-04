package com.fullstack2.webSite.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fullstack2.webSite.oauth2.OAuth2Service;

import static org.springframework.security.config.Customizer.withDefaults;

import java.awt.PageAttributes.MediaType;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    @Autowired
    private final OAuth2Service oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/status", "/view/join", "/auth/join","/my/","/my/main").permitAll()
                        .requestMatchers("/findEmail","/resultFindEmail","/findPw").permitAll()
                        .requestMatchers("/my/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/my/product/**").permitAll()
                        .requestMatchers("/my/productdetail/**").permitAll()
                        .requestMatchers("/my/onlinestore/**").permitAll()
                        
                        
                        .requestMatchers("/view/admin").hasRole("ADMIN")
                        
                       
                        .anyRequest().authenticated()
                )
               .formLogin(login -> login
                        .loginPage("/view/login")
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/view/mainLog", true)
                        .permitAll()
                )
               .oauth2Login(oauth2Configurer -> oauth2Configurer
                       .loginPage("/view/login")
                       
                       .defaultSuccessUrl("/view/mainLog2", true) // 로그인 성공시 이동할 URL
                       .userInfoEndpoint()// 사용자가 로그인에 성공하였을 경우,
                       
                       .userService(oAuth2UserService)// 해당 서비스 로직을 타도록 설정
			)
               .logout()
	         .logoutUrl("/logout")
	          .invalidateHttpSession(true);
     

        return http.build();
    }
   
    
}
