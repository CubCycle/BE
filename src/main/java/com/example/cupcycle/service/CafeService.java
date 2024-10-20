package com.example.cupcycle.service;

import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
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
