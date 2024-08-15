package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class CpuResponse extends PartsResponse{

    private Map<String, Cpu> cpu;

    public CpuResponse(Map<String, Cpu> cpu) {
        this.cpu = cpu;
    }

    @Getter
    public static class Cpu{
        @Field("제조회사")
        String manufacturer;

        @Field("인텔 CPU종류")
        String intelCpuTypes;

        @Field("AMD CPU종류")
        String amdCpuTypes;

        @Field("소켓 구분")
        String socketTypes;

        @Field("제조 공정")
        String manufacturingProcesses;

        @Field("코어 수")
        String coreCounts;

        @Field("스레드 수")
        String threadCounts;

        @Field("기본 클럭")
        String baseClocks;

        @Field("최대 클럭")
        String boostClocks;

        @Field("L3 캐시")
        String l3Caches;

        @Field("TDP")
        String tdps;

        @Field("PCIe 버전")
        String pcieVersions;

        @Field("최대 PCIe 레인수")
        String maxPcieLanes;

        @Field("최대 메모리 크기")
        String maxMemorySizes;

        @Field("메모리 규격")
        String memoryTypes;

        @Field("메모리 클럭")
        String memorySpeeds;

        @Field("내장그래픽")
        String integratedGraphics;

        @Field("GPU 모델명")
        String gpuModels;

        @Field("기술 지원")
        String supportedTechnologies;

        @Field("코드 네임")
        String codeNames;

        @Field("패키지 형태")
        String packageTypes;

        @Field("쿨러")
        String coolerTypes;
    }
}