package travel.domain;

import java.util.List;

public class TripDTO{
	private long id;
	private long user;
	private long destination;
    private String startDate;
    private String endDate;
    private List<VisitDTO> visits;

    public long getId() {
        return id;
    }

    public TripDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public TripDTO setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public TripDTO setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<VisitDTO> getVisits() {
        return visits;
    }

    public TripDTO setVisits(List<VisitDTO> visits) {
        this.visits = visits;
        return this;
    }

    public long getUser() {
        return user;
    }

    public TripDTO setUser(long userId) {
        this.user = userId;
        return this;
    }

    public long getDestination() {
        return destination;
    }

    public TripDTO setDestination(long destinationId) {
        this.destination = destinationId;
        return this;
    }
}