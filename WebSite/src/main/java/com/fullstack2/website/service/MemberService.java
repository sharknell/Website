package com.fullstack2.webSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Optional<Member> findOne(String userId) {
        return repository.findByEmail(userId);
    }
}
