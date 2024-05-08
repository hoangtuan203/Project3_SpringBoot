
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project3.repository;

import com.example.project3.models.Member;
import com.example.project3.models.Xuly;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface XuLyRepository extends JpaRepository<Xuly, Integer> {

    List<Xuly> findByThanhVien(Member tv);
}
