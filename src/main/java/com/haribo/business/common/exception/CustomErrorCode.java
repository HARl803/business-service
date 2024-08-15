package com.haribo.business.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    // 5개가 꽉 찼다
    SIZE_FULL_ERROR("1인당 5개의 기술만 정할 수 있습니다.", HttpStatus.BAD_REQUEST),

    // 커스텀 PC 목록에 해당 이름의 컴퓨터가 없다
    CUSTOM_PC_NOT_FOUND("커스텀 PC 목록에 해당 아이디를 가진 컴퓨터가 없습니다.", HttpStatus.NOT_FOUND),
    
    // 존재하지 않는 유저
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND),

    // 멘토가 아닌 유저
    USER_NOT_MENTO("멘토가 아닌 유저입니다.", HttpStatus.NOT_FOUND),

    // 이미 멘토
    ALREADY_MENTO("이미 멘토인 유저입니다.", HttpStatus.BAD_REQUEST),

    // 존재하지 않는 파츠
    PART_NOT_FOUND("존재하지 않는 파츠입니다", HttpStatus.NOT_FOUND),

    ;

    private final String statusMessage;
    private final HttpStatus status;
}
