package travel.domain;

public class Review {
    private long id;
    private int rating;
    private String comment;
    private Attraction attraction;
    private User user;
    
    public User getUser() {
    	return this.user;
    }
    
    public Review setUser(User user) {
    	this.user = user;
    	return this;
    }

    public long getId() {
        return id;
    }

    public Review setId(long id) {
        this.id = id;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Review setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Review setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public Review setAttraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
    }
}
