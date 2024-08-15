package com.haribo.business.profile.application.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRDto {
    @Id
    @Column(name = "reservation_id")
    @NotNull
    private String reservationId;

    @Column(name = "mento_id")
    @NotNull
    private String mentoId;

    @Column(name = "mentee_id")
    @NotNull
    private String menteeId;

    @Column(name = "status")
    private String status;

    @Column(name = "request")
    private String request;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "modified_date")
    private String modifiedDate;
}
