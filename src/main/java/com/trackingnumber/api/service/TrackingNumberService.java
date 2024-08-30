package com.trackingnumber.api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackingnumber.api.entity.TrackingNumber;
import com.trackingnumber.api.repository.TrackingNumberRepository;

@Service
public class TrackingNumberService {

	@Autowired
	private TrackingNumberRepository repository;

	private long counter = 0L;
	private final Object lock = new Object(); // Object to synchronize on

	public String generateTrackingNumber(String originCountryId, String destinationCountryId, UUID customerId) {
		String trackingNumber;
		synchronized (lock) {
			counter++;
			trackingNumber = generateUniqueTrackingNumber(originCountryId, destinationCountryId, customerId, counter);
		}
		// Return the unique tracking number
		return trackingNumber;
	}

	private String generateUniqueTrackingNumber(String originCountryId, String destinationCountryId, UUID customerId,
			long counter) {
		// Here we concatenate parts of the tracking number
		return originCountryId + destinationCountryId + customerId.toString().substring(0, 8).toUpperCase()
				+ String.format("%06d", counter);
	}

	public TrackingNumber createTrackingNumber(String originCountryId, String destinationCountryId, double weight,
			LocalDateTime createdAt, UUID customerId, String customerName, String customerSlug) {
		String trackingNumber = generateTrackingNumber(originCountryId, destinationCountryId, customerId);

		// Create and save the TrackingNumber entity
		TrackingNumber tn = new TrackingNumber(trackingNumber, createdAt);
		tn.setOriginCountryId(originCountryId);
		tn.setDestinationCountryId(destinationCountryId);
		tn.setWeight(weight);
		tn.setCustomerId(customerId);
		tn.setCustomerName(customerName);
		tn.setCustomerSlug(customerSlug);

		return repository.save(tn);
	}
}
