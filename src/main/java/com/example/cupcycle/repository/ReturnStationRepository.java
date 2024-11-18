package com.example.cupcycle.repository;

import com.example.cupcycle.entity.ReturnStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnStationRepository extends JpaRepository<ReturnStation, Integer> {

    // returnStationId로 반납대 조회
    ReturnStation findByReturnStationId(int returnStationId);

    // 목록 조회
    List<ReturnStation> findAll();

    ReturnStation findByLocation(String location);
}
