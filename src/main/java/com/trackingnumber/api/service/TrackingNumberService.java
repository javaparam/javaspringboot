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

	// Object used for synchronization
	private final Object lock = new Object();

	public String generateTrackingNumber() {
		// The synchronized block ensures that only one thread can execute this block at
		// a time
		synchronized (lock) {
			String trackingNumber;
			do {
				// Generate a tracking number using UUID or another logic
				trackingNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
			} while (repository.existsByTrackingNumber(trackingNumber));
			// Return the unique tracking number
			return trackingNumber;
		}
	}

	public TrackingNumber createTrackingNumber(String originCountryId, String destinationCountryId, double weight,
			LocalDateTime createdAt, UUID customerId, String customerName, String customerSlug) {

		// Generate the unique tracking number within the synchronized block
		String trackingNumber = generateTrackingNumber();

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
