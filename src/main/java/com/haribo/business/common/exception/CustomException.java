package com.haribo.business.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private CustomErrorCode customErrorCode;
    private String statusMessage;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getStatusMessage());
        this.customErrorCode = customErrorCode;
        this.statusMessage = customErrorCode.getStatusMessage();
    }
}
