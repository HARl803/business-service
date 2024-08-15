package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CoolerResponse extends PartsResponse{
    @Field("cooler")
    private Map<String, Cooler> coolerTuning;

    public CoolerResponse(Map<String, Cooler> coolerTuning) {
        this.coolerTuning = coolerTuning;
    }

    @Getter
    public static class Cooler{
        @Field("제조회사")
        String manufacturer;

        @Field("제품 종류")
        String productType;

        @Field("냉각 방식")
        String coolingType;

        @Field("A/S기간")
        String warranty;

        @Field("인텔 소켓")
        String intelSocket;

        @Field("AMD 소켓")
        String amdSocket;

        @Field("높이")
        String height;

        @Field("라디에이터")
        String radiator;

        @Field("팬 크기")
        String fanSize;

        @Field("베어링")
        String bearingType;

        @Field("최대 팬속도")
        String maxFanSpeed;

        @Field("최대 풍량")
        String maxAirFlow;

        @Field("최저 팬소음")
        String minFanNoise;

        @Field("최대 팬소음")
        String maxFanNoise;

        @Field("LED시스템")
        String ledSystem;

        @Field("부가기능")
        String additionalFeatures;

        @Field("써멀유형")
        String thermalType;

        @Field("이미지")
        String image;
    }
}