package com.haribo.business.profile.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mento")
public class MentoNDto {

    @NotNull
    private String id;

    @Field("profile_id")
    private String profileId;

    private int totalCnt;

    private int matchingRate;

    private long star;

    private List<Long> times;

    private List<Integer> techs;

    private List<String> questions;
}
