package com.haribo.business.profile.application.service;

import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import com.haribo.business.profile.presentation.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String profileId);
    void updateProfile(ProfileUpdateRequest profileUpdateRequest);

    ProfileResponse registMento(MentoRequest mentoRequest);
}
