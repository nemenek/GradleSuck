package travel.service;

import travel.persistence.DataStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import travel.domain.Attraction;
import travel.domain.Credentials;
import travel.domain.Destination;
import travel.domain.Review;
import travel.domain.Trip;
import travel.domain.User;

public class DefaultTravelService implements TravelService{
	private User loggedInUser;
	private DataStore dataStore;
	public DefaultTravelService(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
	}
	
	@Override
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	@Override
	public User authenticateUser(Credentials credentials) throws AuthenticationException {
		List<User> users = this.dataStore.getUsers();
		for(User item : users) {
			if(item.getCredentials().getUsername().equals(credentials.getUsername())) {
				if(item.getCredentials().getPassword().equals(credentials.getPassword())) {
					return item;
				}
			}
		}
		throw new AuthenticationException("Authentication Error");
	}
	
	private int getDestinationCount() {
		return this.dataStore.getDestinations().size();
	}
	
	private List<Attraction> getAttractions(){
		List<Attraction> attractions = new ArrayList<>();
		for(Destination dest : this.dataStore.getDestinations()) {
			for(Attraction storedAttraction : dest.getAttractions()) {
				if(!attractions.stream().anyMatch(x -> x.getId() == storedAttraction.getId())) {
					attractions.add(storedAttraction);
				}
			}
		}
		return attractions;
	}
	
	private int getAttractionCount() {
		return getAttractions().size();
	}
	
	private int getUserCount() {
		return this.dataStore.getUsers().size();
	}
	
	private int getReviewCount() {
		return this.dataStore.getReviews().size();
	}

	private int getUserVisitCount(User user) {
		List<Trip> trips = this.dataStore.getTrips();
		int visitCount = 0;
		for(Trip oneTrip : trips) {
			if(oneTrip.getUser().getId() == user.getId()) {
				visitCount += oneTrip.getVisits().size();
			}
		}
		return visitCount;
	}

	private int getUserReviewCount(User user) {
		List<Review> reviews = this.dataStore.getReviews();
		int reviewCount = 0;
		for(Review review : reviews) {
			if(review.getUser().getId() == user.getId()) {
				reviewCount++;
			}
		}
		return reviewCount;
	}
	
	@Override
	public Statistics getStatistics() {
		return new Statistics().setNumberOfAttractions(getAttractionCount()).setNumberOfAllReviews(getReviewCount())
				.setNumberOfDestinations(getDestinationCount()).setNumberOfUsers(getUserCount())
				.setNumberOfUserVisits(getUserVisitCount(loggedInUser)).setNumberOfUserWrittenReviews(getUserReviewCount(loggedInUser));
	}
	@Override
	public List<Destination> getDestinations() {
		return this.dataStore.getDestinations();
	}
	@Override
	public List<Review> getReviews(long attractionId) {
		return this.dataStore.getReviews().stream().filter(x -> x.getAttraction().getId() == attractionId).collect(Collectors.toList());
	}
	@Override
	public void createAttraction(long destinationId, Attraction attraction) {
		this.dataStore.addAttraction(attraction, destinationId);
	}
	@Override
	public List<Trip> getTrips(LocalDate startDate, LocalDate endDate) {
		List<Trip> filteredTrips = this.dataStore.getTrips().stream()
				.filter(x -> x.getUser().getId() == this.getLoggedInUser().getId() &&
						((x.getStartDate().isAfter(startDate) && x.getStartDate().isBefore(endDate)) 
						|| (x.getEndDate().isAfter(startDate) && x.getEndDate().isBefore(endDate))
						|| (x.getStartDate().isBefore(startDate) && x.getEndDate().isAfter(startDate)))).collect(Collectors.toList());
				//.toList();
		//filteredTrips.sort(new SortTrip());  TODO
		return filteredTrips;
	}
	@Override
	public void createTrip(Trip trip) {
		this.dataStore.getTrips().add(trip);
	}

	@Override
	public List<Trip> getTrips() {
		return this.dataStore.getTrips();
	}
}