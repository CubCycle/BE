package com.example.cupcycle.repository;

import com.example.cupcycle.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
