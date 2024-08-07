package com.haribo.business.profile.application.dto;

import java.util.*;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentoInfo {

    private String nickName;

    private String intro;

    private String matchingRate;

    private List<Integer> techs;
}
