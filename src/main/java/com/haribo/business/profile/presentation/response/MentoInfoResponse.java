package com.haribo.business.profile.presentation.response;

import java.util.*;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentoInfoResponse {

    private String nickName;

    private String intro;

    private String matchingRate;

    private String star;

    private List<Integer> techs;
}
