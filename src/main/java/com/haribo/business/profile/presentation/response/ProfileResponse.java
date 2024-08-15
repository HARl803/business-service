package com.haribo.business.profile.presentation.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProfileResponse {

    private final String nickName;

    private final String intro;
}
