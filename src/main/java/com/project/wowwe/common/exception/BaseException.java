package com.project.wowwe.common.exception;

import com.project.wowwe.common.response.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseResponseStatus status;
}