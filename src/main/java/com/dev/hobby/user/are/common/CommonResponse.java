package com.dev.hobby.user.are.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T>{
    private boolean success;
    private T content;
    private String message;

    public static <T> CommonResponse<T> success(T content){
        return CommonResponse.<T>builder()
                .success(true)
                .content(content)
                .build();
    }

    public static <T> CommonResponse<T> fail(String message){
        return CommonResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}
