package com.trackingnumber.api.service;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackingnumber.api.entity.TrackingNumber;
import com.trackingnumber.api.repository.TrackingNumberRepository;



@Service
public class TrackingNumberService {
	
	@Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TrackingNumberRepository repository;

    public String generateTrackingNumber() {
        RLock lock = redissonClient.getLock("trackingNumberLock");
        lock.lock(10, TimeUnit.SECONDS);
        try {
            String trackingNumber;
            do {
                trackingNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
            } while (repository.findByTrackingNumberForUpdate(trackingNumber) != null);
            return trackingNumber;
        } finally {
            lock.unlock();
        }
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
