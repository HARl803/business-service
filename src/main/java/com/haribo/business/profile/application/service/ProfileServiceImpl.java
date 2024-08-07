package com.haribo.business.profile.application.service;

import com.haribo.business.common.exception.CustomErrorCode;
import com.haribo.business.common.exception.CustomException;
import com.haribo.business.profile.application.dto.MentoNDto;
import com.haribo.business.profile.application.dto.ProfileRDto;
import com.haribo.business.profile.domain.repository.ProfileRepository;
import com.haribo.business.profile.presentation.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private final ProfileRepository profileRepository;
    private final MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Override
    public ProfileResponse getProfile(String profileId) {
        ProfileRDto profileRDto = profileRepository.findByProfileId(profileId);

        // 유저 없을 때
        if(profileRDto == null) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        MentoNDto mentoNDto = mongoTemplate.findOne(Query.query(Criteria.where("profileId").is(profileId)), MentoNDto.class);
        if(mentoNDto == null) {
            return ProfileResponse.builder()
                    .nickName(profileRDto.getNickName())
                    .intro(profileRDto.getSimpleIntroduce())
                    .build();
        }

        return null;
    }
}
