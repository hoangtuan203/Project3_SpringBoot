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
    public boolean changePassword(int maTV, String currentPassword, String newPassword) {
        Member member = memberRepository.findById(maTV);

        if (member != null && member.getPassword().equals(currentPassword)) {
            member.setPassword(newPassword);
            memberRepository.save(member); 
            return true; 
        }
        return false; 
    }

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member getPasswordByMaTV(int maTV) {
        return memberRepository.findById(maTV);
    }

    @Override
    public Member loginMember(int maTV, String password) {
        Member member = memberRepository.findById(maTV);
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có đúng không
        if (member != null && member.getPassword().equals(password)) {
            return member; // Trả về người dùng nếu đăng nhập thành công
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }
}