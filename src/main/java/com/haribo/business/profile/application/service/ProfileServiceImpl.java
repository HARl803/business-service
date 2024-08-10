package com.haribo.business.profile.application.service;

import com.haribo.business.common.exception.CustomErrorCode;
import com.haribo.business.common.exception.CustomException;
import com.haribo.business.profile.application.dto.*;
import com.haribo.business.profile.domain.repository.MentoRepository;
import com.haribo.business.profile.domain.repository.ProfileRepository;
import com.haribo.business.profile.domain.repository.ReservationRepository;
import com.haribo.business.profile.presentation.request.MentoMatchingRequest;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import com.haribo.business.profile.presentation.response.MentoInfoResponse;
import com.haribo.business.profile.presentation.response.MentoProfileResponse;
import com.haribo.business.profile.presentation.response.ProfileResponse;
import com.haribo.business.profile.presentation.response.ReviewResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;
    private final MongoTemplate mongoTemplate;
    private final MentoRepository mentoRepository;
    private final ReservationRepository reservationRepository;


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

        MentoRDto mentoRDto = mentoRepository.findByProfileId(profileRDto.getProfileId());

        List<ReservationNDto> allReservation = mongoTemplate.findAll(ReservationNDto.class);
        log.debug("reservation size: {}", allReservation.size());

        List<ReviewResponse> reviewList = new ArrayList<>();

        for(ReservationNDto reservationNDto : allReservation) {
            LinkedHashMap<String, Object> reservation = (LinkedHashMap<String, Object>) reservationNDto.getReservation();
            log.debug("reservation: {}", reservation);

            String key = (String) reservation.keySet().toArray()[0];

            ReservationRDto reservationRDto = reservationRepository.findByReservationId(key);

            if (reservationRDto == null) continue;

            String menteeProfileId = reservationRDto.getMenteeId();
            String menteeNickName = profileRepository.findByProfileId(menteeProfileId).getNickName();

            if (reservationRDto.getMentoId().equals(profileId)) {
                reviewList.add(ReviewResponse.builder()
                        .writerName(menteeNickName)
                        .content(reservationNDto.getReview().getContent())
                        .star(reservationNDto.getReview().getStar())
                        .createdDate(reservationNDto.getReview().getCreatedDate())
                        .build());
            }
        }

        String matchingRate;
        if(mentoNDto.getTotalCnt()==0) matchingRate = "멘토링이 한번도 이루어진적이 없어요";
        else {
            double matchingRateDouble = (double)mentoNDto.getMatchingRate()/ mentoNDto.getTotalCnt();
            matchingRate = Math.round(matchingRateDouble * 100) +"%";
        }

        return MentoProfileResponse.builder()
                .profileId(profileId)
                .nickName(profileRDto.getNickName())
                .description(mentoRDto.getDescription())
                .intro(profileRDto.getIntro())
                .matchingRate(matchingRate)
                .star(Math.round((double)mentoNDto.getStar()/ mentoNDto.getMatchingRate()* 100)/100.0+"")
                .techs(mentoNDto.getTechs())
                .times(mentoNDto.getTimes())
                .questions(mentoNDto.getQuestions())
                .reviews(reviewList)
                .build();
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

        log.debug("업데이트 할 profileRDto: {}", profileRDto);

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
                        .build()
        );

        mongoTemplate.save(
                MentoNDto.builder()
                        .profileId(profileId)
                        .times(mentoRequest.getTimes())
                        .techs(mentoRequest.getTechs())
                        .questions(mentoRequest.getQuestions())
                        .matchingRate(0)
                        .totalCnt(0)
                .star(0)
                .build()
        );

        log.debug("멘토 등록 완료: {}", mentoRequest);

        return MentoProfileResponse.builder()
                .nickName(profileRDto.getNickName())
                .intro(profileRDto.getIntro())
                .profileId(profileId)
                .times(mentoRequest.getTimes())
                .techs(mentoRequest.getTechs())
                .star("0")
                .questions(mentoRequest.getQuestions())
                .description(mentoRequest.getDescription())
                .matchingRate("매칭 시도 횟수가 한 번도 없어여!")
                .reviews(new ArrayList<>())
                .build();
    }

    @Override
    public List<Map<String, MentoInfoResponse>> getMentoList() {

        List<Map<String, MentoInfoResponse>> allMentoList = new ArrayList<>();

        List<MentoRDto> MentoRDtoRList = mentoRepository.findAll();

        for(MentoRDto mentoRDto : MentoRDtoRList){
            String profileId = mentoRDto.getProfileId();
            log.debug("profileId: {}", profileId);

            ProfileRDto profileRDto = profileRepository.findByProfileId(profileId);
            MentoNDto mentoNDto = mongoTemplate.findOne(Query.query(Criteria.where("profileId").is(profileId)), MentoNDto.class);

            String matchingRate;
            if(mentoNDto.getTotalCnt()==0) matchingRate = "매칭 시도 횟수가 한 번도 없어여!";
            else {
                double matchingRateDouble = (double)mentoNDto.getMatchingRate()/ mentoNDto.getTotalCnt();
                matchingRate = Math.round(matchingRateDouble*100)+"%";
            }

            List<Integer> techs = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("profile_id").is(profileId)), MentoNDto.class).getTechs();

            Map<String, MentoInfoResponse> tmpMentoMap = new HashMap<>();
            MentoInfoResponse mentoInfoResponse = MentoInfoResponse.builder()
                    .intro(profileRDto.getIntro())
                    .star(Math.round((double)mentoNDto.getStar()/ mentoNDto.getMatchingRate()*100)/100.0+"")
                    .matchingRate(matchingRate)
                    .nickName(profileRDto.getNickName())
                    .techs(techs)
                    .build();
            tmpMentoMap.put(profileId, mentoInfoResponse);

            allMentoList.add(tmpMentoMap);
        }

        return allMentoList;
    }

    @Override
    @Transactional
    public void updateMento(MentoRequest mentoRequest) {

        String profileId = mentoRequest.getProfileId();
        ProfileRDto profileRDto = profileRepository.findByProfileId(profileId);

        if(profileRDto==null) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        MentoRDto mentoRDto = mentoRepository.findByProfileId(profileId);

        if(mentoRDto==null) throw new CustomException(CustomErrorCode.USER_NOT_MENTO);

        if(mentoRequest.getTechs().size()>5)
            throw new CustomException(CustomErrorCode.SIZE_FULL_ERROR);

        mentoRepository.save(
                MentoRDto.builder()
                        .mentoId(mentoRDto.getMentoId())
                        .description(mentoRequest.getDescription())
                        .profileId(profileId)
                        .build()
        );

        Query query = new Query(Criteria.where("profile_id").is(profileId));

        MentoNDto mentoNDto = mongoTemplate.findOne(query, MentoNDto.class);

        MentoNDto tmpMentoDto = MentoNDto
                .builder()
                .id(mentoNDto.getId())
                .profileId(profileId)
                .totalCnt(mentoNDto.getTotalCnt())
                .matchingRate(mentoNDto.getMatchingRate())
                .star(mentoNDto.getStar())
                .times(mentoRequest.getTimes())
                .techs(mentoRequest.getTechs())
                .questions(mentoRequest.getQuestions())
                .build();

        mongoTemplate.save(tmpMentoDto);
    }

    @Override
    public void feedbackMentoMatching(MentoMatchingRequest mentoMatchingRequest) {

        String profileId = mentoMatchingRequest.getProfileId();
        if(profileId==null) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        Query query = new Query(Criteria.where("profile_id").is(profileId));

        MentoNDto mentoNDto = Objects.requireNonNull(mongoTemplate.findOne(query, MentoNDto.class));

        Update update = new Update();
        if(mentoMatchingRequest.getMatchingCompleted()) {
            update.set("totalCnt", mentoNDto.getTotalCnt()+1);
            update.set("matchingRate", mentoNDto.getMatchingRate()+1);
            update.set("star", mentoNDto.getStar()+mentoMatchingRequest.getStar());
        } else {
            update.set("totalCnt", mentoNDto.getTotalCnt()+1);
        }

        mongoTemplate.updateFirst(query, update, MentoNDto.class);
    }

}
