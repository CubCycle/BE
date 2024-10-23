package com.example.cupcycle.repository;

import com.example.cupcycle.entity.Cup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupRepository extends JpaRepository<Cup, Integer> {
}
