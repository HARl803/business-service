package com.haribo.business.profile.presentation.response;

import com.haribo.business.profile.application.dto.ReservationNDto.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ReviewResponse extends Review {

    private String writerName;
}
