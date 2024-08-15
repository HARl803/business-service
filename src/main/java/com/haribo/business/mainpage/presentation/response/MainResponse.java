package com.haribo.business.mainpage.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.LinkedHashMap;

@AllArgsConstructor
@Builder
public class MainResponse {

    LinkedHashMap<String, PartsResponse> parts;
}
