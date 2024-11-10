package com.example.cupcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnStationDto {
    private String location;
    private int currentCup;
    private ReturnStationStatus status;
    public enum ReturnStationStatus {
        FULL, AVAILABLE;
    }
}
