package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CaseResponse extends PartsResponse{
    @Field("case")
    private Map<String, Case> pcCase;

    public CaseResponse(Map<String, Case> pcCase) {
        this.pcCase = pcCase;
    }

    @Getter
    public static class Case{
        @Field("제조회사")
        String manufacturer;

        @Field("제품 분류")
        String productType;

        @Field("케이스 크기")
        String caseSize;

        @Field("지원파워규격")
        String powerSupply;

        @Field("지원보드규격")
        String boardSupport;

        @Field("133cm베이")
        String bay13cm;

        @Field("89cm베이")
        String bay89cm;

        @Field("64cm베이")
        String bay64cm;

        @Field("저장장치 장착")
        String storageMount;

        @Field("수직 PCI형태")
        String verticalPCI;

        @Field("쿨링팬")
        String coolingFans;

        @Field("LED팬")
        String ledFans;

        @Field("전면 패널 타입")
        String frontPanel;

        @Field("측면 개폐 방식")
        String sideOpening;

        @Field("측면")
        String side;

        @Field("후면")
        String rear;

        @Field("전면")
        String front;

        @Field("상단")
        String top;

        @Field("먼지필터")
        String dustFilter;

        @Field("VGA 장착 길이")
        String vgaMountLength;

        @Field("CPU쿨러 장착높이")
        String cpuCoolerHeight;

        @Field("수랭쿨러 규격")
        String liquidCoolerSupport;

        @Field("라디에이터(상단)")
        String radiatorTop;

        @Field("라디에이터(전면)")
        String radiatorFront;

        @Field("라디에이터(후면)")
        String radiatorRear;

        @Field("부가기능")
        String additionalFeatures;

        @Field("LED 튜닝")
        String ledTuning;

        @Field("컨트롤러 지원")
        String controllerSupport;

        @Field("케이스 색상 계열")
        String caseColor;

        @Field("너비(W)")
        String width;

        @Field("깊이(D)")
        String depth;

        @Field("높이(H)")
        String height;

        @Field("이미지")
        String image;
    }
}