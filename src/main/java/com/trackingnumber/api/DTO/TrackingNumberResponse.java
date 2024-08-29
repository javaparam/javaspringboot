package com.trackingnumber.api.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingNumberResponse {
    private String trackingNumber;
    private LocalDateTime createdAt;
}
