package com.haribo.business.mainpage.presentation.response;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "parts")
@Getter
public class MotherboardResponse extends PartsResponse{

    private Map<String, Motherboard> motherboard;

    public MotherboardResponse(Map<String, Motherboard> motherboard) {
        this.motherboard = motherboard;
    }

    @Getter
    public static class Motherboard{
        @Field("제조회사")
        String manufacturer;

        @Field("제품 분류")
        String productType;

        @Field("CPU 소켓")
        String cpuSocket;

        @Field("세부 칩셋")
        String chipset;

        @Field("폼팩터")
        String formFactor;

        @Field("전원부")
        String powerPhase;

        @Field("Vcore출력합계")
        String vcoreOutput;

        @Field("메모리 종류")
        String memoryType;

        @Field("메모리 속도")
        String memorySpeed;

        @Field("메모리 슬롯")
        String memorySlots;

        @Field("메모리 용량")
        String memoryCapacity;

        @Field("VGA 연결")
        String vgaConnection;

        @Field("PCIe버전")
        String pcieVersion;

        @Field("SATA3")
        String sata;

        @Field("M2")
        String m2;

        @Field("그래픽 출력")
        String graphicOutput;

        @Field("후면단자")
        String rearPorts;

        @Field("USB3x 20Gbps")
        String rearUsb3_20Gbps;

        @Field("USB2")
        String rearUsb2_0;

        @Field("유선랜 칩셋")
        String wiredLanChipset;

        @Field("무선랜 종류")
        String wirelessLanType;

        @Field("오디오 칩셋")
        String audioChipset;

        @Field("LED 시스템")
        String ledSystem;

        @Field("이미지")
        String image;
    }
}