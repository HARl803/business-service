package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class PowerResponse extends PartsResponse{

    private Map<String, Power> power;

    public PowerResponse(Map<String, Power> power) {
        this.power = power;
    }

    @Getter
    public static class Power{
        @Field("제조회사")
        String manufacturer;

        @Field("제품 분류")
        String productType;

        @Field("정격출력")
        String ratedOutput;

        @Field("80PLUS인증")
        String certification80Plus;

        @Field("ETA인증")
        String certificationETA;

        @Field("LAMBDA인증")
        String certificationLAMBDA;

        @Field("+12V 출력방식")
        String outputType12V;

        @Field("PFC회로")
        String pfcCircuit;

        @Field("쿨링팬 크기")
        String coolingFanSize;

        @Field("A/S 보증기간")
        String warrantyPeriod;

        @Field("케이블연결")
        String cableConnection;

        @Field("메인전원")
        String mainPower;

        @Field("보조전원")
        String auxiliaryPower;

        @Field("PCIe 16핀(12+4)")
        String pcie16pin;

        @Field("부가기능")
        String additionalFeatures;

        @Field("LED 시스템")
        String ledSystem;

        @Field("이미지")
        String image;
    }
}