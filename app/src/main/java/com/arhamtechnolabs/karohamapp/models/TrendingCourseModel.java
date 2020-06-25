package com.arhamtechnolabs.karohamapp.models;

public class TrendingCourseModel {


    private String id, title, short_description, description, language, section, price, discounted_price, level, video_url, status;

    public TrendingCourseModel() {

    }

    public TrendingCourseModel(String id, String title, String short_description, String description, String language, String section, String price, String discounted_price, String level, String video_url, String status) {
        this.id = id;
        this.title = title;
        this.short_description = short_description;
        this.description = description;
        this.language = language;
        this.section = section;
        this.price = price;
        this.discounted_price = discounted_price;
        this.level = level;
        this.video_url = video_url;
        this.status = status;
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

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
