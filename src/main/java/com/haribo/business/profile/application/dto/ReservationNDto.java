package com.haribo.business.profile.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservation")
public class ReservationNDto {

    String id;

    Object reservation;

    Object log;

    Review review;

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    public static class Review {

        private String star;
        private String content;
        @Field("created_date")
        private String createdDate;
    }
}
