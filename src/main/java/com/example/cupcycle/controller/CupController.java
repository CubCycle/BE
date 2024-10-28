package com.example.cupcycle.controller;


import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.CupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cup")
@RequiredArgsConstructor
public class CupController {
    private final CupService cupService;

    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<String>> borrowCup(@RequestParam int cafeId, @RequestParam int studentId,
                                                         @RequestParam int cupId) {

        ApiResponse<String> response = cupService.borrowCup(cafeId, studentId, cupId);
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
