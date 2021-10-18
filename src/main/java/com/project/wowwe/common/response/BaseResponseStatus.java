package com.project.wowwe.common.response;


import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    VIDEO_NOT_EXIST(false, 2000,"존재하지 않는 비디오입니다."),
    VIDEO_ERROR(false,2001,"비디오 처리 에러입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}