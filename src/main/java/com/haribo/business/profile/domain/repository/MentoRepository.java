package com.haribo.business.profile.domain.repository;

import com.haribo.business.profile.application.dto.MentoRDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MentoRepository extends JpaRepository<MentoRDto, String> {

    boolean existsByProfileId(String profileId);
    MentoRDto findByProfileId(String profileId);

    @Query(value = "SELECT * FROM mento_member ORDER BY regist_date DESC LIMIT 3", nativeQuery = true)
    List<MentoRDto> findTop3ByOrderByregistDatetDesc();
}
