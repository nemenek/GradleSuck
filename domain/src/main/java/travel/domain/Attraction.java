package travel.domain;

public class Attraction {
    private long id;
    private String name;
    private String description;
    private Category category;

    public long getId() {
        return id;
    }

    public Attraction setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Attraction setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Attraction setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Attraction setCategory(Category category) {
        this.category = category;
        return this;
    }
}
