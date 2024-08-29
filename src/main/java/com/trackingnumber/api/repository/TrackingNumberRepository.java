package com.trackingnumber.api.repository;

import com.trackingnumber.api.entity.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
    boolean existsByTrackingNumber(String trackingNumber);
}
