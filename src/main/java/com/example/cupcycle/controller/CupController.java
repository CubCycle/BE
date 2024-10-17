package com.example.cupcycle.controller;


import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cup")

public class CupController {

    @Autowired
    private CupService cupService;

    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<String>> borrowCup(@RequestParam int cafeId, @RequestParam int studentId,
                                                         @RequestParam int cupId) {
        //컵 1회 사용당 carbon_reduction을 29g으로 측정하여, kg으로 변환하여, 인자로 0.029를 줌
        ApiResponse<String> response = cupService.borrowCup(cafeId, studentId, cupId, 0.029);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse<String>> returnCup(
            @RequestParam int cupId,
            @RequestParam int returnStationId) {

        ApiResponse<String> response = cupService.returnCup(cupId, returnStationId);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/updateCupStatus")
    public ResponseEntity<ApiResponse<String>> updateCupStatusAndReward(@RequestParam int cupId) {
        ApiResponse<String> response = cupService.updateCupStatusAndReward(cupId);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }


}
