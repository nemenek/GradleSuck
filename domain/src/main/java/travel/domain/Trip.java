package travel.domain;

import java.time.LocalDate;
import java.util.List;

public class Trip {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Visit> visits;
    private User user;
    private Destination destination;

    public long getId() {
        return id;
    }

    public Trip setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Trip setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Trip setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public Trip setVisits(List<Visit> visits) {
        this.visits = visits;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Trip setUser(User user) {
        this.user = user;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public Trip setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }
}
