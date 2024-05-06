package com.example.project3.service;
import com.example.project3.models.Member;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    List<Member> getAllMember();
    Member getPasswordByMaTV(int maTV);
    Member loginMember(String email, String password);

}