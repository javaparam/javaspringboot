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
    
    public String generateTrackingNumber() {
        String trackingNumber;
        do {
            // Logic to generate tracking number, e.g., using UUID or custom logic
            trackingNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
        } while (repository.existsByTrackingNumber(trackingNumber));
        
        return trackingNumber;
    }

    public TrackingNumber createTrackingNumber(String originCountryId, String destinationCountryId, double weight, LocalDateTime createdAt, UUID customerId, String customerName, String customerSlug) {
        String trackingNumber = generateTrackingNumber();
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
