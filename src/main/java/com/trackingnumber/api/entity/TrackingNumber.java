package com.trackingnumber.api.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TrackingNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String trackingNumber;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	private UUID customerId;
	private String customerName;
	private String customerSlug;
	private String originCountryId;
	private String destinationCountryId;
	private double weight;

	public TrackingNumber(String trackingNumber, LocalDateTime createdAt) {
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
	}

}
