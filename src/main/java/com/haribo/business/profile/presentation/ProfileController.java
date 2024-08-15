package com.haribo.business.profile.presentation;

import com.haribo.business.common.auth.AuthService;
import com.haribo.business.profile.application.service.ProfileService;
import com.haribo.business.profile.presentation.request.MentoMatchingRequest;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> getProfile (@CookieValue("JSESSIONID") String sessionId)
            throws URISyntaxException {

        LinkedHashMap<String, String> profile = authService.authorizedProfileId(sessionId);

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(profile.get("profileId")));
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest,
                                           @CookieValue("JSESSIONID") String sessionId)
            throws URISyntaxException {

        LinkedHashMap<String, String> profile = authService.authorizedProfileId(sessionId);

        profileService.updateProfile(profileUpdateRequest, profile.get("profileId"));

        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> registMento(@RequestBody MentoRequest mentoRequest,
                                         @CookieValue("JSESSIONID") String sessionId)
            throws URISyntaxException {

        LinkedHashMap<String, String> profile = authService.authorizedProfileId(sessionId);

        return ResponseEntity.status(HttpStatus.OK).body(profileService.registMento(mentoRequest, profile.get("profileId")));
    }

    @GetMapping
    public ResponseEntity<?> getAllMento() {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getMentoList());
    }

    @PatchMapping("/mento")
    public ResponseEntity<?> updateMento(@RequestBody MentoRequest mentoRequest,
                                         @CookieValue("JSESSIONID") String sessionId)
            throws URISyntaxException {

        LinkedHashMap<String, String> profile = authService.authorizedProfileId(sessionId);

        profileService.updateMento(mentoRequest, profile.get("profileId"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/mento/feedback/matching")
    public ResponseEntity<?> feedbackMentoMatching(@RequestBody MentoMatchingRequest mentoMatchingRequest,
                                                   @CookieValue("JSESSIONID") String sessionId)
            throws URISyntaxException {

        LinkedHashMap<String, String> profile = authService.authorizedProfileId(sessionId);

        profileService.feedbackMentoMatching(mentoMatchingRequest,profile.get("profileId"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
