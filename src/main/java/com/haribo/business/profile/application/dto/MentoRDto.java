package com.haribo.business.profile.application.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentomember")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoRDto {
    @Id
    @Column(name = "mento_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mentoId;

    @Column(name = "profile_id", unique = true)
    @NotNull
    private String profileId;

    @Column(name = "introduce")
    @NotNull
    private String description;

    @Column(name = "regist_date")
    @NotNull
    private LocalDateTime registDate;
}
