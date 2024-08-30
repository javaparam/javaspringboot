package com.trackingnumber.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.trackingnumber.api.entity.TrackingNumber;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {


	boolean existsByTrackingNumber(@Param("trackingNumber") String trackingNumber);
}
