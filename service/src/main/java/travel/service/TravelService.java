package travel.service;

import travel.domain.User;

import java.time.LocalDate;
import java.util.List;

import travel.domain.Attraction;
import travel.domain.Credentials;
import travel.domain.Destination;
import travel.domain.Review;
import travel.domain.Trip;


public interface TravelService{
	User authenticateUser(Credentials credentials) throws AuthenticationException;
	Statistics getStatistics();
	List<Destination> getDestinations();
	List<Trip> getTrips();
	List<Review> getReviews(long attractionId);
	void createAttraction(long destinationId, Attraction attraction);
	List<Trip> getTrips(LocalDate startDate, LocalDate endDate);
	void createTrip(Trip trip);
	void setLoggedInUser(User user);
	User getLoggedInUser();
}