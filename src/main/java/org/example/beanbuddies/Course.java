package org.example.beanbuddies;

public class Course {
    private String title;
    private String instructor;
    private String duration;
    private String level;
    private String description;
    private String imageUrl;
    private String previewUrl;
    private double price;
    private double rating;
    private int enrollments;

    public Course(String title, String instructor, String duration, String level, String description, String imageUrl, String previewUrl) {
        this.title = title;
        this.instructor = instructor;
        this.duration = duration;
        this.level = level;
        this.description = description;
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
        this.price = 0.0;
        this.rating = 0.0;
        this.enrollments = 0;
    }

    public Course(String title, String instructor, String duration, String level, String description, String imageUrl, String previewUrl, double price, double rating, int enrollments) {
        this.title = title;
        this.instructor = instructor;
        this.duration = duration;
        this.level = level;
        this.description = description;
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
        this.price = price;
        this.rating = rating;
        this.enrollments = enrollments;
    }

    // getters & setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getPreviewUrl() { return previewUrl; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public int getEnrollments() { return enrollments; }
    public void setEnrollments(int enrollments) { this.enrollments = enrollments; }

    @Override
    public String toString() {
        return title;
    }
}
