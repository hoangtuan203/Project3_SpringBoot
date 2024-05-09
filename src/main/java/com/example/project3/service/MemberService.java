package com.example.project3.service;

import com.example.project3.models.Member;
import com.example.project3.models.ThietBi;
import com.example.project3.models.ThongTinSD;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    List<Member> getAllMember();

    Member getMemberById(int maTV);

    Member getPasswordByMaTV(int maTV);

    Member loginMember(int maTV, String password);

    List<ThietBi> getAllThietBi();
  
    List<ThietBi> getAllThietBiDangRanh();
  
    ThongTinSD insert(ThongTinSD thongTinSD);
  
    boolean changePassword(int maTV, String currentPassword, String newPassword);
}


   

