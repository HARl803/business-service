package com.haribo.business.profile.presentation;

import com.haribo.business.profile.application.service.ProfileService;
import com.haribo.business.profile.presentation.request.MentoMatchingRequest;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfile(@PathVariable String profileId) {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(profileId));
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest) {

        profileService.updateProfile(profileUpdateRequest);

        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> registMento(@RequestBody MentoRequest mentoRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.registMento(mentoRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllMento() {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getMentoList());
    }

    @PatchMapping("/mento")
    public ResponseEntity<?> updateMento(@RequestBody MentoRequest mentoRequest) {
        profileService.updateMento(mentoRequest);

        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/mento/feedback/matching")
    public ResponseEntity<?> feedbackMentoMatching(@RequestBody MentoMatchingRequest mentoMatchingRequest) {
        profileService.feedbackMentoMatching(mentoMatchingRequest);

        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

}
