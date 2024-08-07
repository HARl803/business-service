package com.haribo.business.profile.application.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "profilemember")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRDto {
    @Id
    @Column(name = "profile_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String profileId;

    @Column(name = "member_uid", unique = true)
    @NotNull
    private String memberId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "nick_name", unique = true)
    @NotNull
    private String nickName;

    @Column(name = "simple_introduce")
    private String simpleIntroduce;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "status")
    private String status;
}
