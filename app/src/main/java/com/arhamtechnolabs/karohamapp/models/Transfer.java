package com.arhamtechnolabs.karohamapp.models;

public class Transfer {

    String desc;

    public Transfer(){}

    public Transfer(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        System.out.println("get Data");
        return desc;
    }

    public void setDesc(String desc) {
        System.out.println("set Data");
        this.desc = desc;
    }
}
