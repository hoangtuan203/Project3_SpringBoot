package com.example.project3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project3.models.Member;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>,CrudRepository<Member, Integer>{
    Member findById(int id);
    
    List<Member> findByMaTV(int maTV);
    Member findByEmail(String email);


    boolean existsByMaTV(int maTV);
}