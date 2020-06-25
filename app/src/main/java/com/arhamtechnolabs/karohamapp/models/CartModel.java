package com.arhamtechnolabs.karohamapp.models;

public class CartModel {

    String courseId, courseTitle, coursePrice, courseDiscountFlag, courseDiscountPrice, userId, isFreeCourse, thumbnail;
    String ratings;

    public CartModel(String courseId, String courseTitle, String coursePrice, String courseDiscountFlag, String courseDiscountPrice, String userId, String isFreeCourse, String thumbnail) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.coursePrice = coursePrice;
        this.courseDiscountFlag = courseDiscountFlag;
        this.courseDiscountPrice = courseDiscountPrice;
        this.userId = userId;
        this.isFreeCourse = isFreeCourse;
        this.thumbnail = thumbnail;
    }

    public CartModel(String courseId, String courseTitle, String coursePrice, String courseDiscountFlag, String courseDiscountPrice, String userId, String isFreeCourse, String thumbnail, String ratings) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.coursePrice = coursePrice;
        this.courseDiscountFlag = courseDiscountFlag;
        this.courseDiscountPrice = courseDiscountPrice;
        this.userId = userId;
        this.isFreeCourse = isFreeCourse;
        this.thumbnail = thumbnail;
        this.ratings = ratings;
    }

    public CartModel(CourseModel model) {
        courseId = model.getId();
        courseTitle = model.getTitle();
        coursePrice = model.getPrice();
        courseDiscountFlag = model.getDiscount_flag();
        courseDiscountPrice = model.getDiscounted_price();
        userId = model.getUser_id();
        isFreeCourse = model.getIs_free_course();
        thumbnail = model.getThumbnail();
        ratings = model.getRating();
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseDiscountFlag() {
        return courseDiscountFlag;
    }

    public void setCourseDiscountFlag(String courseDiscountFlag) {
        this.courseDiscountFlag = courseDiscountFlag;
    }

    public String getCourseDiscountPrice() {
        return courseDiscountPrice;
    }

    public void setCourseDiscountPrice(String courseDiscountPrice) {
        this.courseDiscountPrice = courseDiscountPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsFreeCourse() {
        return isFreeCourse;
    }

    public void setIsFreeCourse(String isFreeCourse) {
        this.isFreeCourse = isFreeCourse;
    }
}
