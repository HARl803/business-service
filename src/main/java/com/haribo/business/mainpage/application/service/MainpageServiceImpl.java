package com.haribo.business.mainpage.application.service;

import com.haribo.business.profile.application.dto.MentoNDto;
import com.haribo.business.profile.application.dto.MentoRDto;
import com.haribo.business.profile.application.dto.ProfileRDto;
import com.haribo.business.profile.domain.repository.MentoRepository;
import com.haribo.business.profile.domain.repository.ProfileRepository;
import com.haribo.business.profile.presentation.response.MentoInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainpageServiceImpl implements MainpageService {

    private final RestTemplate restTemplate;
    private final MentoRepository mentoRepository;
    private final ProfileRepository profileRepository;
    private final MongoTemplate mongoTemplate;

    @Value("${parts.url}")
    private String PARTS_URL;

    @Value("${community.url}")
    private String COMMUNITY_URL;

    @Override
    public JSONObject getMain() {
        log.info("불러와야 하는 정보: 신규 등록한 멘토 top 3정보, 랜덤 부품 9개, 커뮤니티 신규 게시글 상위 5개");

        JSONObject jsonParts = new JSONObject();
        JSONObject response = new JSONObject();

        List<MentoInfoResponse> top3MentoList = getRecentTop3MentoList();
        log.debug("가장 최근 등록한 멘토 3명 리스트: {}", getRecentTop3MentoList());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String partsName = getRandomPartsName();

        ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> partsResponse = restTemplate.exchange(
                URI.create(PARTS_URL+partsName),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        Set<Integer> uniqueNums = getRandomNums(partsResponse.getBody().get(partsName).size());

        Set<String> entrySet = partsResponse.getBody().get(partsName).keySet();

        LinkedHashMap<String, LinkedHashMap<String, String>> linkedHashMap = partsResponse.getBody().get(partsName);

        int idx = 0;
        for (String key1 : entrySet) {
            if(!uniqueNums.contains(idx++)) continue;

            log.info("{}", key1);

            JSONObject jsonPart = new JSONObject();
            LinkedHashMap<String, String> map = linkedHashMap.get(key1);

            jsonPart.put("partName", "요즘 뜨는 " + partsName + idx);

            int cnt = 1;
            for (String key2 : map.keySet()) {
                if(cnt > 5) break;
                if (map.get(key2) != null) {
                    jsonPart.put("property" + cnt, map.get(key2));
                    cnt++;
                }
            }
            jsonPart.put("image", map.get("image"));

            jsonParts.put(key1, jsonPart.toMap());
        }

        log.info("community_url :{}", COMMUNITY_URL);



        ResponseEntity<List<LinkedHashMap<String, String>>> communityResponse = restTemplate.exchange(
                URI.create(COMMUNITY_URL),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<LinkedHashMap<String, String>>>() {}
        );

        LinkedHashMap<String, JSONObject> jsonCommunitylist = new LinkedHashMap<>();
        for(LinkedHashMap<String, String> map : communityResponse.getBody()) {
            JSONObject jsonCommunity = new JSONObject();
            String postId="";
            for(Map.Entry<String, String> entry : map.entrySet()) {
                if(entry.getKey().equals("postId")){
                    postId = entry.getValue();
                } else {
                    jsonCommunity.put(entry.getKey(), entry.getValue());
                }
            }
            jsonCommunitylist.put(postId, jsonCommunity);
        }

        response.put("parts", jsonParts);
        response.put("mentoLists", top3MentoList);
        response.put("boardLists", jsonCommunitylist);

        return response;
    }

    private List<MentoInfoResponse> getRecentTop3MentoList() {

        List<MentoRDto> recentMentoList = mentoRepository.findTop3ByOrderByregistDatetDesc();
        List<MentoInfoResponse> mentoInfoResponseList = new ArrayList<>();

        for (MentoRDto mento : recentMentoList) {
            String profileId = mento.getProfileId();
            ProfileRDto profileRDto = profileRepository.findByProfileId(profileId);
            MentoNDto mentoNDto = mongoTemplate.findOne(new Query(Criteria.where("profileId").is(profileId)), MentoNDto.class);

            mentoInfoResponseList.add(
                    MentoInfoResponse.builder()
                            .nickName(profileRDto.getNickName())
                            .intro(profileRDto.getIntro())
                            .star(Math.round((double) mentoNDto.getStar()/mentoNDto.getMatchingRate()*100)/100.0+"")
                            .techs(mentoNDto.getTechs())
                            .matchingRate(Math.round((double) mentoNDto.getStar()/mentoNDto.getMatchingRate())+"%")
                            .build()
            );
        }

        return mentoInfoResponseList;
    }

    private String getRandomPartsName(){

        Map<Integer, String> parts = new HashMap<>();
        parts.put(0, "cpu");
        parts.put(1, "memory");
        parts.put(2, "case");
        parts.put(3, "coolerTuning");
        parts.put(4, "hdd");
        parts.put(5, "ssd");
        parts.put(6, "power");
        parts.put(7, "motherboard");
        parts.put(8, "gpu");

        return parts.get(new Random().nextInt(parts.size()));
    }

    private Set<Integer> getRandomNums(int size){

        log.info("랜덤 넘버 만들거에요: {}", size);

        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        while (uniqueNumbers.size() < 3) {
            int number = random.nextInt(size);
            uniqueNumbers.add(number);
        }

        return uniqueNumbers;
    }

}
