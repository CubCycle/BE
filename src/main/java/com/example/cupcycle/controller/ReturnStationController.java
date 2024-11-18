package com.example.cupcycle.controller;

import com.example.cupcycle.dto.ReturnStationDto;
import com.example.cupcycle.entity.ReturnStation;
import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.ReturnStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returnStation")
@RequiredArgsConstructor
public class ReturnStationController {
    private final ReturnStationService returnStationService;

    /*
    * 반납대의 재고 상태 조회
    */

    @GetMapping("/getStatus")
    public ResponseEntity<ApiResponse<Integer>> getAvailableCups(@RequestParam int id) {
        int availableCups = returnStationService.getAvailableCups(id);

        if (availableCups <= 0) {  // 반납대가 꽉찬 경우 (재고 X)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,5001, "해당 반납대에 반납할 수 없습니다.", 0));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.",availableCups));
    }

    /*
     * 반납대 정보 조회
     */
    @GetMapping("/getInfo")
    public ResponseEntity<ApiResponse<ReturnStationDto>> getReturnStations(@RequestParam int returnStationId) {
        ReturnStationDto returnStation = returnStationService.getReturnStationInfo(returnStationId);

        if (returnStation == null) {  // 반납대를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,5001, "해당 반납대를 찾을 수 없습니다."));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.", returnStation));
    }

    /*
    * 반납대의 목록 조회
    */
    @GetMapping("/getReturnStationList")
    public ResponseEntity<ApiResponse<List<ReturnStationDto>>> getReturnStationList() {
        List<ReturnStationDto> returnStations = returnStationService.getReturnStationList();
        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.", returnStations));
    }

    /*
     * 반납대 id 반환
     */
    @GetMapping("/getId")
    public ResponseEntity<ApiResponse<Integer>> getReturnStationId(@RequestParam String location) {
        Integer returnStationId = returnStationService.getReturnStationId(location);

        if (returnStationId == null) {  // 반납대를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,5001, "해당 위치의 반납대를 찾을 수 없습니다."));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.", returnStationId));
    }

    /*
    * 반납대 초기화
    */
    @PostMapping("/initReturnStation")
    public ResponseEntity<ApiResponse<Void>> initReturnStation(
            @RequestParam int returnStationId) {
        try {
            returnStationService.initReturnStation(returnStationId);
            return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, 200, "반납대가 초기화 되었습니다."));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, 6001, e.getMessage()));
        }
    }
}
