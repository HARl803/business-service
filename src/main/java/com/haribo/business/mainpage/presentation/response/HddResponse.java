package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class HddResponse extends PartsResponse{

    private Map<String, Hdd> hdd;

    public HddResponse(Map<String, Hdd> hdd) {
        this.hdd = hdd;
    }

    @Getter
    public static class Hdd{
        @Field("제조회사")
        String manufacturer;

        @Field("제품 분류")
        String productType;

        @Field("디스크 크기")
        String diskSize;

        @Field("디스크 용량")
        String diskCapacity;

        @Field("인터페이스")
        String interfaceType;

        @Field("회전수")
        String rotationSpeed;

        @Field("버퍼 용량")
        String bufferSize;

        @Field("전송 속도")
        String transferSpeed;

        @Field("기록방식")
        String recordingMethod;

        @Field("디스크 수")
        String diskCount;

        @Field("부가기능")
        String additionalFeatures;

        @Field("사용보증")
        String mtbf;

        @Field("A/S 정보")
        String warranty;

        @Field("이미지")
        String image;
    }
}