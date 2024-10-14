package com.example.cupcycle.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean isSuccess;
    private int code;
    private String message;
    private T data;

    // data 필드 없이 사용할 때
    public ApiResponse(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = null; // data 필드가 없는 경우 null로 설정
    }

    // data 필드를 포함할 때
    public ApiResponse(boolean isSuccess, int code, String message, T data) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

