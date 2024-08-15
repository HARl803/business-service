package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class MemoryResponse extends PartsResponse{

    private Map<String, Memory> memory;

    public MemoryResponse(Map<String, Memory> memory) {
        this.memory = memory;
    }

    @Getter
    public static class Memory{
        @Field("제조회사")
        String manufacturer;

        @Field("사용 장치")
        String deviceType;

        @Field("제품 분류")
        String productTypes;

        @Field("메모리 용량")
        String memoryCapacity;

        @Field("동작클럭(대역폭)")
        String operatingClock;

        @Field("램타이밍")
        String ramTiming;

        @Field("동작전압")
        String operatingVoltage;

        @Field("램개수")
        String numberOfModules;

        @Field("부가기능")
        String features;

        @Field("히트싱크")
        String heatSink;

        @Field("방열판 색상")
        String heatSinkColor;

        @Field("LED색상")
        String ledColor;

        @Field("LED 시스템")
        String ledSystem;

        @Field("이미지")
        String image;
    }
}