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

    @Override
    public void saveMember(Member member) {
        Member m = new Member();
        m.setMaTV(member.getMaTV());
        m.setEmail(member.getEmail());
        m.setPassword(member.getPassword());
        memberRepository.save(m);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map((member) -> convertEntityToDto(member))
                .collect(Collectors.toList());
    }

    private Member convertEntityToDto(Member member) {
        Member m = new Member();
        member.setMaTV(m.getMaTV());
        member.setEmail(m.getEmail());
        member.setPassword(m.getPassword());
        return member;
    }
}