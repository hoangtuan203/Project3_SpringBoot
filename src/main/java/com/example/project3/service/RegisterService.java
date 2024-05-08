package com.example.project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project3.models.Member;
import com.example.project3.repository.MemberRepository;

@Service
public interface RegisterService {

    Member saveMember(Member member);
    boolean isMemberExit(int maTV);
}
