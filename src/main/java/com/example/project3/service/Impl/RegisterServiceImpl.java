package com.example.project3.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.project3.models.Member;
import com.example.project3.repository.MemberRepository;
import com.example.project3.service.RegisterService;

@Service
class RegisterServiceImpl implements RegisterService {
    private MemberRepository memberRepository;
    public RegisterServiceImpl( MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public Member saveMember(Member member) {
        if (!isMemberExit(member.getMaTV())) {
            return memberRepository.save(member);
        }

        return null;
    }

    @Override
    public boolean isMemberExit(int maTV) {
        return memberRepository.existsById(maTV);
    }

}