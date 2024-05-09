package com.example.project3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project3.models.Member;
import com.example.project3.models.ThietBi;
import com.example.project3.models.ThongTinSD;

import java.util.List;
@Repository
public interface TTSDRepository extends JpaRepository<ThongTinSD,Integer>,CrudRepository<ThongTinSD, Integer> {
    ThongTinSD save(ThongTinSD thongTinSD);
}
