package com.trackingnumber.api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackingnumber.api.DTO.TrackingNumberResponse;
import com.trackingnumber.api.entity.TrackingNumber;
import com.trackingnumber.api.service.TrackingNumberService;

@RestController
public class TrackingNumberController {

	@Autowired
	private TrackingNumberService service;

	@GetMapping("/next-tracking-number")
	public TrackingNumberResponse getNextTrackingNumber(@RequestParam String originCountryId,
			@RequestParam String destinationCountryId, @RequestParam double weight, @RequestParam String createdAt,
			@RequestParam UUID customerId, @RequestParam String customerName, @RequestParam String customerSlug) {

		LocalDateTime timestamp = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);

		TrackingNumber tn = service.createTrackingNumber(originCountryId, destinationCountryId, weight, timestamp,
				customerId, customerName, customerSlug);
		return new TrackingNumberResponse(tn.getTrackingNumber(), tn.getCreatedAt());
	}
}
