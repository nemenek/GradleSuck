package travel.domain;

import java.time.LocalDate;

public class Visit {
    private long id;
    private LocalDate visitDate;
    private Attraction  attraction;

    public long getId() {
        return id;
    }

    public Visit setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Visit setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
        return this;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public Visit setAttraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
    }
}

