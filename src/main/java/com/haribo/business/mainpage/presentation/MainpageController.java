package com.haribo.business.mainpage.presentation;

import com.haribo.business.mainpage.application.service.MainpageService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
public class MainpageController {

    private final MainpageService mainpageService;

    @GetMapping
    public ResponseEntity<?> getMain() throws JSONException {

        return ResponseEntity.status(HttpStatus.OK).body(mainpageService.getMain().toMap());
    }

}
