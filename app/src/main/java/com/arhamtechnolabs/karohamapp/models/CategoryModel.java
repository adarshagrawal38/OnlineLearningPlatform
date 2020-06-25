package com.arhamtechnolabs.karohamapp.models;

public class CategoryModel {

    private String id, code, name, thumbnail;

    public CategoryModel(String id, String code, String name, String thumbnail) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
