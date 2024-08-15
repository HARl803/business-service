package com.haribo.business.profile.application.service;

import com.haribo.business.profile.presentation.request.MentoMatchingRequest;
import com.haribo.business.profile.presentation.response.MentoInfoResponse;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import com.haribo.business.profile.presentation.response.ProfileResponse;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    ProfileResponse getProfile(String profileId);
    void updateProfile(ProfileUpdateRequest profileUpdateRequest, String profileId);

    ProfileResponse registMento(MentoRequest mentoRequest, String profileId);
    List<Map<String, MentoInfoResponse>> getMentoList();
    void updateMento(MentoRequest mentoRequest, String profileId);

    void feedbackMentoMatching(MentoMatchingRequest mentoMatchingRequest, String profileId);
}
