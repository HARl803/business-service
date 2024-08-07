package com.haribo.business.profile.domain.repository;

import com.haribo.business.profile.application.dto.ProfileRDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends JpaRepository<ProfileRDto, String> {

    ProfileRDto findByProfileId(String profileId);
}
