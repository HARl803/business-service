package com.haribo.business.profile.application.service;

import com.haribo.business.profile.presentation.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String profileId);
}
