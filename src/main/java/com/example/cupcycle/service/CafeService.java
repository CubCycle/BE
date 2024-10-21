package com.example.cupcycle.service;

import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    private static final int ADMIN_CODE = 1234;

    public boolean verifyAdminCode(int adminCode) {
        return adminCode == ADMIN_CODE;
    }

    public Optional<Cafe> findCafeByName(String name) {
        return cafeRepository.findByName(name);
    }

    /*
     * 카페의 다회용컵 재고 상태 조회
     */
    public int getAvailableCups(int id) {
        Cafe cafe = cafeRepository.findByCafeId(id);

        if (cafe == null) {
            return 0;
        }

        return cafe.getAvailableCups();
    }
}
