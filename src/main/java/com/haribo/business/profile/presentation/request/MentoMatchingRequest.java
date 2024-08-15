package com.haribo.business.profile.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MentoMatchingRequest {

    private Boolean matchingCompleted;

    private int star;

}
