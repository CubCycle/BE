package com.example.cupcycle.service;

import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CafeService {
    @Autowired
    private CafeRepository cafeRepository;

    private final int ADMIN_CODE = 1234;

    public boolean verifyAdminCode(int adminCode) {
        return adminCode == ADMIN_CODE;
    }

    public Optional<Cafe> findCafeByName(String name) {
        return cafeRepository.findByName(name);
    }


}
