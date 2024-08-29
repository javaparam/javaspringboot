package com.trackingnumber.api.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trackingnumber.api.entity.TrackingNumber;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TrackingNumber t WHERE t.trackingNumber = :trackingNumber")
    TrackingNumber findByTrackingNumberForUpdate(@Param("trackingNumber") String trackingNumber);
}
