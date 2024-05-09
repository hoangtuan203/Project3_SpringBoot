package com.example.project3.service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.project3.models.Member;
import com.example.project3.models.ThietBi;
import com.example.project3.models.ThongTinSD;
import com.example.project3.repository.MemberRepository;
import com.example.project3.repository.TBRepository;
import com.example.project3.repository.TTSDRepository;
import com.example.project3.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private TBRepository thietbiRepository;
    private TTSDRepository ttsdRepository;
    public MemberServiceImpl(MemberRepository memberRepository,TBRepository thietbiRepository,TTSDRepository ttsdRepository) {
        this.memberRepository = memberRepository;
        this.thietbiRepository = thietbiRepository;
        this.ttsdRepository = ttsdRepository;
    }

    @Override
    public boolean changePassword(int maTV, String currentPassword, String newPassword) {
        Member member = memberRepository.findById(maTV).orElse(null);

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
    public List<ThietBi> getAllThietBi() {
        return thietbiRepository.findAll();
    }
    @Override
    public List<ThietBi> getAllThietBiDangRanh() {
        return thietbiRepository.findAllThietBiDangRanh();
    }
    @Override
    public ThongTinSD insert(ThongTinSD thongTinSD) {
        return ttsdRepository.save(thongTinSD);
    }
    @Override
    public Member getMemberById(int maTV) {
        
        Member member = memberRepository.findByMaTV(maTV);
      
        return member;
    }
    @Override
    public Member getPasswordByMaTV(int maTV) {
        return memberRepository.findByMaTV(maTV);
    }

    @Override
    public Member loginMember(int maTV, String password) {
        Member member = memberRepository.findByMaTV(maTV);
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có đúng không
        if (member != null && member.getPassword().equals(password)) {
            return member; // Trả về người dùng nếu đăng nhập thành công
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }
}