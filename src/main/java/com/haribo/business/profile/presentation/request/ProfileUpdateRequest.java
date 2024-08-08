package com.haribo.business.profile.presentation.request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileUpdateRequest {

    private String profileId;

    private String nickName;

    private String intro;

    private String profileImg;
}
