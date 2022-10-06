package travel.domain;

public class VisitDTO {
    private long id;
    private String visitDate;
    private long  attraction;

    public long getId() {
        return id;
    }

    public VisitDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public VisitDTO setVisitDate(String visitDate) {
        this.visitDate = visitDate;
        return this;
    }

    public long getAttraction() {
        return attraction;
    }

    public VisitDTO setAttraction(long attraction) {
        this.attraction = attraction;
        return this;
    }
}