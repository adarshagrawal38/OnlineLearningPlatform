package com.arhamtechnolabs.karohamapp.models;

public class CourseModel {


    String id, title, short_description, description, outcomes, language, category_id, sub_category_id, section, requirements, price, discount_flag, discounted_price, level, user_id, thumbnail, video_url, date_added, last_modified, visibility, is_top_course, is_free_course, course_overview_provider, status, rating, name;

    public CourseModel(String id, String title, String short_description, String description, String outcomes, String language, String category_id, String sub_category_id, String section, String requirements, String price, String discount_flag, String discounted_price, String level, String user_id, String thumbnail, String video_url, String date_added, String last_modified, String visibility, String is_top_course, String is_free_course, String course_overview_provider, String status, String rating, String name) {
        this.id = id;
        this.title = title;
        this.short_description = short_description;
        this.description = description;
        this.outcomes = outcomes;
        this.language = language;
        this.category_id = category_id;

        this.sub_category_id = sub_category_id;
        this.section = section;
        this.requirements = requirements;
        this.price = price;
        this.discount_flag = discount_flag;
        this.discounted_price = discounted_price;
        this.level = level;
        this.user_id = user_id;
        this.thumbnail = thumbnail;
        this.video_url = video_url;
        this.date_added = date_added;
        this.last_modified = last_modified;
        this.visibility = visibility;
        this.is_top_course = is_top_course;
        this.is_free_course = is_free_course;
        this.course_overview_provider = course_overview_provider;
        this.status = status;
        this.rating = rating;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(String sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount_flag() {
        return discount_flag;
    }

    public void setDiscount_flag(String discount_flag) {
        this.discount_flag = discount_flag;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getIs_top_course() {
        return is_top_course;
    }

    public void setIs_top_course(String is_top_course) {
        this.is_top_course = is_top_course;
    }

    public String getIs_free_course() {
        return is_free_course;
    }

    public void setIs_free_course(String is_free_course) {
        this.is_free_course = is_free_course;
    }

    public String getCourse_overview_provider() {
        return course_overview_provider;
    }

    public void setCourse_overview_provider(String course_overview_provider) {
        this.course_overview_provider = course_overview_provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
