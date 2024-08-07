package com.haribo.business.profile.presentation;

import com.haribo.business.profile.application.service.ProfileService;
import com.haribo.business.profile.presentation.request.MentoRequest;
import com.haribo.business.profile.presentation.request.ProfileUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfile(@PathVariable String profileId) {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(profileId));
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest) {

        profileService.updateProfile(profileUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("업데이트 완료");
    }

    @PostMapping
    public ResponseEntity<?> registMento(@RequestBody MentoRequest mentoRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.registMento(mentoRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllMento() {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getMentoList());
    }


}
