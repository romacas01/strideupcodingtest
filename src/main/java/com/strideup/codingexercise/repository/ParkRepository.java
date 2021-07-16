package com.strideup.codingexercise.repository;

import com.strideup.codingexercise.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {
    public Park getByParkCode(String parkCode);
    public List<Park> findAll();
}
