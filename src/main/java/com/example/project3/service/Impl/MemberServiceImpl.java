package com.example.project3.service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.project3.models.Member;
import com.example.project3.repository.MemberRepository;
import com.example.project3.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }
    @Override
    public Member getPasswordByMaTV(int maTV) {
        return memberRepository.findById(maTV);
    }

   
}
