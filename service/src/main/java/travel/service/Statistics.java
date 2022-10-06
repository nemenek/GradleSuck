package travel.service;

public class Statistics{
	private int numberOfAttractions = -1;
	private int numberOfUsers = -1;
	private int numberOfDestinations = -1;
	private int numberOfAllReviews = -1;
	private int numberOfUserVisits = -1;
	private int numberOfUserWrittenReviews = -1;
	
	public Statistics() {}

	public int getNumberOfAttractions() {
		return numberOfAttractions;
	}

	public Statistics setNumberOfAttractions(int attractionCount) {
		this.numberOfAttractions = attractionCount;
		return this;
	}

	public int getNumberOfDestinations() {
		return numberOfDestinations;
	}

	public Statistics setNumberOfDestinations(int destinationCount) {
		this.numberOfDestinations = destinationCount;
		return this;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public Statistics setNumberOfUsers(int userCount) {
		this.numberOfUsers = userCount;
		return this;
	}

	public int getNumberOfUserVisits() {
		return numberOfUserVisits;
	}

	public Statistics setNumberOfUserVisits(int userVisitCount) {
		this.numberOfUserVisits = userVisitCount;
		return this;
	}

	public int getNumberOfUserWrittenReviews() {
		return this.numberOfUserWrittenReviews;
	}

	public Statistics setNumberOfUserWrittenReviews(int userReviewCount) {
		this.numberOfUserWrittenReviews = userReviewCount;
		return this;
	}

	public int getNumberOfAllReviews() {
		return this.numberOfAllReviews;
	}

	public Statistics setNumberOfAllReviews(int reviewCount) {
		this.numberOfAllReviews = reviewCount;
		return this;
	}

	
	
}