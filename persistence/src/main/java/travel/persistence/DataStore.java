package travel.persistence;

import java.util.List;

import travel.domain.Attraction;
import travel.domain.Destination;
import travel.domain.Review;
import travel.domain.Trip;
import travel.domain.User;

public interface DataStore {
    void loadData();
    
    void storeData();

	List<User> getUsers();

	List<Destination> getDestinations();
	
	List<Review> getReviews();

	List<Trip> getTrips();
	
	void addAttraction(Attraction attraction, long destinationID);
	
	void addReview(Review review);
	
	void addTrip(Trip trip);
}
