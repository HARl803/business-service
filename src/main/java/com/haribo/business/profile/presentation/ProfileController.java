package com.haribo.business.profile.presentation;

import com.haribo.business.profile.application.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /// 일반 유저 인원 컨트롤러
    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfile(@PathVariable String profileId) {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(profileId));
    }
}
