package com.haribo.business.profile.application.service;

import com.haribo.business.common.exception.CustomErrorCode;
import com.haribo.business.common.exception.CustomException;
import com.haribo.business.profile.application.dto.MentoInfo;
import com.haribo.business.profile.application.dto.MentoNDto;
import com.haribo.business.profile.application.dto.MentoRDto;
import com.haribo.business.profile.application.dto.ProfileRDto;
import com.haribo.business.profile.domain.repository.MentoRepository;
import com.haribo.business.profile.domain.repository.ProfileRepository;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import com.haribo.business.profile.presentation.response.MentoProfileResponse;
import com.haribo.business.profile.presentation.response.ProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private final ProfileRepository profileRepository;
    private final MongoTemplate mongoTemplate;
    private final MentoRepository mentoRepository;

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
                    .intro(profileRDto.getIntro())
                    .build();
        }

        return null;
    }

    @Override
    @Transactional
    public void updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        ProfileRDto profileRDto = profileRepository.findByProfileId(profileUpdateRequest.getProfileId());

        if(profileRDto==null) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        profileRDto = ProfileRDto.builder()
                .profileId(profileRDto.getProfileId())
                .memberId(profileRDto.getMemberId())
                .email(profileRDto.getEmail())
                .name(profileRDto.getName())
                .status(profileRDto.getStatus())
                .nickName(profileUpdateRequest.getNickName())
                .profileImg(profileUpdateRequest.getProfileImg())
                .intro(profileUpdateRequest.getIntro())
                .build();

        logger.debug("업데이트 할 profileRDto: {}", profileRDto);

        profileRepository.save(profileRDto);
    }

    @Override
    @Transactional
    public MentoProfileResponse registMento(MentoRequest mentoRequest) {
        ProfileRDto profileRDto = profileRepository.findByProfileId(mentoRequest.getProfileId());

        if(profileRDto==null) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        String profileId = profileRDto.getProfileId();

        if(mentoRepository.existsByProfileId(profileId))
            throw new CustomException(CustomErrorCode.ALREADY_MENTO);

        if(mentoRequest.getTechs().size()>5)
            throw new CustomException(CustomErrorCode.SIZE_FULL_ERROR);

        mentoRepository.save(
                MentoRDto.builder()
                        .description(mentoRequest.getDescription())
                        .profileId(profileId)
                        .matchingRate(0)
                        .totalCnt(0)
                        .build()
        );

        // times 로직 -> 들어온 시간 모두 비트 연산으로 or 연산해서 합산 한 값
        // 프론트에서 처리해서 주는거겠지,,?
        // 만약 그렇다면 난 DB에 저장만 하면 됨..
        /////////

        mongoTemplate.save(
                MentoNDto.builder()
                        .profileId(profileId)
                        .times(mentoRequest.getTimes())
                        .techs(mentoRequest.getTechs())
                        .questions(mentoRequest.getQuestions())
                        .build()
        );

        logger.debug("멘토 등록 완료: {}", mentoRequest);

        return MentoProfileResponse.builder()
                .nickName(profileRDto.getNickName())
                .intro(profileRDto.getIntro())
                .profileId(profileId)
                .times(mentoRequest.getTimes())
                .techs(mentoRequest.getTechs())
                .questions(mentoRequest.getQuestions())
                .description(mentoRequest.getDescription())
                .matchingRate("매칭 시도 횟수가 한 번도 없어여!")
                .reviews(new ArrayList<>())
                .build();
    }

    @Override
    public List<Map<String, MentoInfo>> getMentoList() {

        List<Map<String, MentoInfo>> allMentoList = new ArrayList<>();

        List<MentoRDto> MentoRDtoRList = mentoRepository.findAll();
        ;
        for(MentoRDto mentoRDto : MentoRDtoRList){
            String profileId = mentoRDto.getProfileId();
            logger.debug("profileId: {}", profileId);

            ProfileRDto profileRDto = profileRepository.findByProfileId(profileId);

            String matchingRate;
            if(mentoRDto.getTotalCnt()==0) matchingRate = "매칭 시도 횟수가 한 번도 없어여!";
            else matchingRate = (double)mentoRDto.getMatchingRate()/ mentoRDto.getTotalCnt() +"%";

            List<Integer> techs = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("profile_id").is(profileId)), MentoNDto.class).getTechs();

            Map<String, MentoInfo> tmpMentoMap = new HashMap<>();
            MentoInfo mentoInfo = MentoInfo.builder()
                    .intro(profileRDto.getIntro())
                    .matchingRate(matchingRate)
                    .nickName(profileRDto.getNickName())
                    .techs(techs)
                    .build();
            tmpMentoMap.put(profileId, mentoInfo);

            allMentoList.add(tmpMentoMap);
        }

        return allMentoList;
    }
}
