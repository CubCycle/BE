package com.example.cupcycle.service;

import com.example.cupcycle.dto.ReturnStationDto;
import com.example.cupcycle.entity.ReturnStation;
import com.example.cupcycle.repository.ReturnStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnStationService {
    private final ReturnStationRepository returnStationRepository;
    private static final int MAX_CUPS = 20; // 최대 반납 가능 수 : 일단 20으로 설정
    @Autowired
    public ReturnStationService(ReturnStationRepository returnStationRepository) {
        this.returnStationRepository = returnStationRepository;
    }

    /*
     * 반납대의 재고 상태 조회
     */
    public int getAvailableCups(int returnStationId) {
        ReturnStation returnStation = returnStationRepository.findByReturnStationId(returnStationId);
        if (returnStation == null) {
            return 0; // 반납대가 없을 경우 0 리턴
        }
        return MAX_CUPS - returnStation.getCurrentCup();
    }

    /*
     * 반납대의 목록 조회
     */
    public List<ReturnStationDto> getReturnStationList() {
        List<ReturnStation> returnStations = returnStationRepository.findAll();
        return returnStations.stream().map(station -> new ReturnStationDto(
                station.getLocation(),
                station.getCurrentCup(),
                station.getStatus().toString()
        )).collect(Collectors.toList());
    }

    /*
     * 반납대 정보 조회
     */
    public ReturnStationDto getReturnStationInfo(int returnStationId) {
        ReturnStation returnStation = returnStationRepository.findById(returnStationId).orElse(null);

        if (returnStation == null) {
            return null;
        }
        return new ReturnStationDto(returnStation.getLocation(), returnStation.getCurrentCup(), returnStation.getStatus().toString());
    }

    /*
     * 반납대 id 반환
     */
    public Integer getReturnStationId(String location) {
        ReturnStation returnStation = returnStationRepository.findByLocation(location);

        if (returnStation == null) {
            return null;
        }
        return returnStation.getReturnStationId();
    }


    /*
     * 반납대 초기화
     */
    public void initReturnStation(int returnStationId) {
        ReturnStation returnStation = returnStationRepository.findByReturnStationId(returnStationId);

        returnStation.setCurrentCup(0);
        returnStation.setStatus(ReturnStation.ReturnStationStatus.AVAILABLE);
        returnStationRepository.save(returnStation);
    }
}
