package com.arhamtechnolabs.karohamapp.models;

public class AllCourseModel {

    String id, title, discounted_price, level, rating;

    public AllCourseModel(String id, String title, String discounted_price, String level, String rating) {
        this.id = id;
        this.title = title;
        this.discounted_price = discounted_price;
        this.level = level;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
