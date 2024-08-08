package com.haribo.business.profile.domain.repository;

import com.haribo.business.profile.application.dto.MentoRDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MentoRepository extends JpaRepository<MentoRDto, String> {

    boolean existsByProfileId(String profileId);
    MentoRDto findByProfileId(String profileId);
}
