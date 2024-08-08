package com.haribo.business.profile.domain.repository;

import com.haribo.business.profile.application.dto.ReservationRDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationRDto, String> {

    ReservationRDto findByReservationId(String reservationId);
}
