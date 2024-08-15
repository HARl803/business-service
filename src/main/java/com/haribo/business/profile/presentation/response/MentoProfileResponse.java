package com.haribo.business.profile.presentation.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@SuperBuilder
public class MentoProfileResponse extends ProfileResponse {

    private String profileId;

    private String description;

    private String matchingRate;

    private String star;

    private List<Long> times;

    private List<Integer> techs;

    private List<String> questions;

    private List<ReviewResponse> reviews;
}
