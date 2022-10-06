package travel.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import travel.domain.Attraction;
import travel.domain.Destination;
import travel.domain.Review;
import travel.domain.ReviewDTO;
import travel.domain.Trip;
import travel.domain.TripDTO;
import travel.domain.User;
import travel.domain.Visit;
import travel.domain.VisitDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDataStore implements DataStore {

    private List<Destination> destinations;
    private List<User> users;
    private List<Trip> trips;
    private List<Review> reviews;
    
    private final String destinationsPath = "destinations.json";
    private final String usersPath = "users.json";
    private final String tripsPath = "trips.json";
    private final String reviewsPath = "reviews.json";
    private final String basePath;

    public FileDataStore(String basePath) {
        this.basePath = basePath;
    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void storeData() {
    	this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
    	//Save destinations and attractions
    	String json = "[\n";
    	int idx = 1;
    	for(Destination dest : this.destinations) {
    		try {
				json += this.objectMapper.writeValueAsString(dest) + (idx == this.destinations.size() ? "\n" : ",\n");
				++idx;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
    	}
    	json += "]";
    	try {
			FileOutputStream out = new FileOutputStream(String.format("%s/%s", this.basePath, this.destinationsPath), false);
			out.write(json.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//save Users, unnecessary??
//    	json = "[\n";
//    	idx = 1;
//    	for(User user : this.users) {
//    		try {
//				json += this.objectMapper.writeValueAsString(user) + (idx == this.users.size() ? "\n" : ",\n");
//				++idx;
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//    	}
//    	json += "]";
//    	System.out.println(json);
//    	try {
//			FileOutputStream out = new FileOutputStream(String.format("%s/%s", this.basePath, this.usersPath), false);
//			out.write(json.getBytes());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	
    	
    	//save trips
    	List<TripDTO> dtoTrips = parseToTripDTO(this.trips);
    	
    	json = "[\n";
    	idx = 1;
    	for(TripDTO trip : dtoTrips) {
    		try {
				json += this.objectMapper.writeValueAsString(trip) + (idx == dtoTrips.size() ? "\n" : ",\n");
				++idx;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
    	}
    	json += "]";
    	try {
			FileOutputStream out = new FileOutputStream(String.format("%s/%s", this.basePath, this.tripsPath), false);
			out.write(json.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private List<TripDTO> parseToTripDTO(List<Trip> trips2) {
		List<TripDTO> returnValue = new ArrayList<>();
		List<VisitDTO> visits = new ArrayList<>();
		for(Trip trip : this.trips) {
			for(Visit visit : trip.getVisits()) {
				visits.add(new VisitDTO().setId(visit.getId())
						.setAttraction(visit.getAttraction().getId())
						.setVisitDate(visit.getVisitDate().toString()));
			}
			returnValue.add(new TripDTO().setId(trip.getId()).setVisits(visits)
					.setDestination(trip.getDestination().getId())
					.setUser(trip.getUser().getId())
					.setStartDate(trip.getStartDate().toString())
					.setEndDate(trip.getEndDate().toString()));
			visits = new ArrayList<>();
		}
		return returnValue;
	}
    

	@Override
    public void loadData() {
    	List<TripDTO> dtoTrips = null;
    	List<ReviewDTO> dtoReviews = null;
    	try {
    		this.destinations = Arrays.asList(this.objectMapper.readValue(new File(buildPath(this.destinationsPath)), Destination[].class));
		
			this.users = Arrays.asList(this.objectMapper.readValue(new File(buildPath(this.usersPath)), User[].class));
			
			dtoTrips = Arrays.asList(this.objectMapper.readValue(new File(buildPath(this.tripsPath)), TripDTO[].class));
			 
			dtoReviews = Arrays.asList(this.objectMapper.readValue(new File(buildPath(this.reviewsPath)), ReviewDTO[].class));
			
			this.trips = parseTripsDTO(dtoTrips);
			
			this.reviews = parseReviewDTO(dtoReviews);
			 
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }
    
    private List<Review> parseReviewDTO(List<ReviewDTO> dtoReviews) {
		List<Review> returnValue = new ArrayList<>();
		for(ReviewDTO item : dtoReviews) {
			Attraction attr = null;
			for(Destination dest : this.destinations) {
				for(Attraction attraction : dest.getAttractions()) {
					if(attraction.getId() == item.getAttraction()) {
						attr = attraction;
						break;
					}
				}
			}
			returnValue.add(new Review().setId(item.getId())
					.setComment(item.getComment())
					.setRating(item.getRating())
					.setUser(this.users.stream().filter(x -> x.getId() == item.getUser())
							.findFirst().get())
					.setAttraction(attr));
		}
		return returnValue;
	}

	private List<Trip> parseTripsDTO(List<TripDTO> dtoTrips) {
    	List<Trip> returnValue = new ArrayList<>();
    	for(TripDTO item : dtoTrips) {
    		returnValue.add(
					 new Trip()
					 .setId(item.getId())
					 .setStartDate(LocalDate.parse(item.getStartDate()))
					 .setEndDate(LocalDate.parse(item.getEndDate()))
					 .setVisits(parseVisitDTO(item.getVisits()))
					 .setDestination(this.destinations
							 .stream().filter(x -> x.getId() == item.getDestination())
							 .findFirst().get())
					 .setUser(this.users
							 .stream().filter(x -> x.getId() == item.getUser())
							 .findFirst().get()));
		 }
		return returnValue;
	}

	private List<Visit> parseVisitDTO(List<VisitDTO> visits) {
    	List<Visit> returnValue = new ArrayList<>();
		for(VisitDTO item : visits) {
			Attraction attr = null;
			for(Destination dest : this.destinations) {
				for(Attraction attraction : dest.getAttractions()) {
					if(attraction.getId() == item.getAttraction()) {
						attr = attraction;
						break;
					}
				}
			}
			returnValue.add(new Visit().setId(item.getId())
					.setVisitDate(LocalDate.parse(item.getVisitDate()))
					.setAttraction(attr));
		}
		return returnValue;
	}

	private String buildPath(String fileName) {
    	return String.format("%s/%s", this.basePath, fileName);
    }
	
	@Override
	public List<User> getUsers() {
		return this.users;
	}

	@Override
	public List<Destination> getDestinations() {
		return this.destinations;
	}

	@Override
	public List<Review> getReviews(){
		return this.reviews;
	}

	@Override
	public List<Trip> getTrips(){
		return this.trips;
	}

	@Override
	public void addAttraction(Attraction attraction, long destinationID) {
		this.destinations.stream().filter(x->x.getId() == destinationID).findFirst().get().getAttractions().add(attraction);
	}

	@Override
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	@Override
	public void addTrip(Trip trip) {
		this.trips.add(trip);
	}
}
