package com.arhamtechnolabs.karohamapp.models;

public class Duration {

    String section, duration, total_lession;

    public Duration(String section, String duration, String total_lession) {
        this.section = section;
        this.duration = duration;
        this.total_lession = total_lession;
    }

    public String getTotal_lession() {
        return total_lession;
    }

    public void setTotal_lession(String total_lession) {
        this.total_lession = total_lession;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
