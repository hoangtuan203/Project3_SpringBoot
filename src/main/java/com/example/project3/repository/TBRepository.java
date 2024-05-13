package com.example.project3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project3.models.Member;
import com.example.project3.models.ThietBi;

import java.util.List;
@Repository
public interface TBRepository extends JpaRepository<ThietBi,Integer>,CrudRepository<ThietBi, Integer> {
    ThietBi findByMaTB(int maTB);

    @Query(value = "SELECT t.* FROM ThietBi t WHERE t.maTB NOT IN (SELECT td.maTB FROM ThongTinsd td WHERE td.maTB != 0 AND td.maTB IS NOT NULL AND (td.tgTra IS NULL OR td.tgDatCho IS NOT NULL))", nativeQuery = true)
    List<ThietBi> findAllThietBiDangRanh(); 
    
}
