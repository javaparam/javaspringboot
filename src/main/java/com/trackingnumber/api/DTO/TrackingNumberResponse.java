package com.trackingnumber.api.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingNumberResponse {
	private String trackingNumber;
	private LocalDateTime createdAt;

	public TrackingNumberResponse(String trackingNumber, LocalDateTime createdAt) {
		super();
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
