package com.trackingnumber.api.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSlug() {
		return customerSlug;
	}

	public void setCustomerSlug(String customerSlug) {
		this.customerSlug = customerSlug;
	}

	public String getOriginCountryId() {
		return originCountryId;
	}

	public void setOriginCountryId(String originCountryId) {
		this.originCountryId = originCountryId;
	}

	public String getDestinationCountryId() {
		return destinationCountryId;
	}

	public void setDestinationCountryId(String destinationCountryId) {
		this.destinationCountryId = destinationCountryId;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public TrackingNumber(String trackingNumber, LocalDateTime createdAt) {
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
	}

}
