package com.haribo.business.profile.presentation.request;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MentoRequest {

    private String description;

    private List<Long> times;

    private List<Integer> techs;

    private List<String> questions;
}
