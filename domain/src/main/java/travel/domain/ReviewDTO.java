package travel.domain;

public class ReviewDTO {
    private long id;
    private long user;
    private int rating;
    private String comment;
    private long attraction;
    
    
    public long getUser() {
    	return this.user;
    }
    
    public void setUser(long user) {
    	this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getAttraction() {
        return attraction;
    }

    public void setAttraction(long attraction) {
        this.attraction = attraction;
    }
}