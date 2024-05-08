/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project3.repository;

import com.example.project3.models.Thietbi;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface ThietBiRepository extends JpaRepository<Thietbi, Integer> {

    @Override
    Optional<Thietbi> findById(Integer id);

    @Override
    List<Thietbi> findAll();

}
