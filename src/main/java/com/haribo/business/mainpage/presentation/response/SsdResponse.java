package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class SsdResponse extends PartsResponse{

    private Map<String, Ssd> ssd;

    public SsdResponse(Map<String, Ssd> ssd) {
        this.ssd = ssd;
    }

    @Getter
    public static class Ssd{
        @Field("제조회사")
        String manufacturer;

        @Field("폼팩터")
        String formFactor;

        @Field("인터페이스")
        String interfaceType;

        @Field("프로토콜")
        String protocol;

        @Field("용량")
        String capacity;

        @Field("메모리 타입")
        String memoryType;

        @Field("낸드 구조")
        String nandStructure;

        @Field("공정")
        String process;

        @Field("RAM 탑재")
        String ramEmbedded;

        @Field("RAM 타입")
        String ramType;

        @Field("컨트롤러")
        String controller;

        @Field("순차읽기")
        String sequentialRead;

        @Field("순차쓰기")
        String sequentialWrite;

        @Field("읽기IOPS")
        String readIOPS;

        @Field("쓰기IOPS")
        String writeIOPS;

        @Field("지원기능")
        String supportFeatures;

        @Field("부가기능")
        String additionalFeatures;

        @Field("MTBF")
        String mtbf;

        @Field("TBW")
        String tbw;

        @Field("A/S기간")
        String warranty;

        @Field("이미지")
        String image;
    }
}