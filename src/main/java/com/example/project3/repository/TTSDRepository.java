package com.example.project3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project3.models.ThongtinSD;

@Repository
public interface TTSDRepository extends JpaRepository<ThongtinSD,Integer>,CrudRepository<ThongtinSD, Integer> {
    @SuppressWarnings("unchecked")
    ThongtinSD save(ThongtinSD thongtinSD);
}
