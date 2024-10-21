package com.example.cupcycle.repository;

import com.example.cupcycle.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    Optional<Cafe> findByName(String name);
}
