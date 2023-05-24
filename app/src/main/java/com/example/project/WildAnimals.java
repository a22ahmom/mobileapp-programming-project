package com.example.project;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class WildAnimals {
    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    @SerializedName("size")
    private int meter;
    @SerializedName("cost")
    private int feet;
    private Auxdata auxdata;
    public WildAnimals(String name){
        this.name = name;
    }

    public WildAnimals(String name, String location, String category){
        this.name = name;
        this.location = location;
        this.category = category;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Auxdata getAuxdata() {
        return auxdata;
    }

    public String getLocation(){ return location; }

    public String getCategory(){ return category; }

    @NonNull
    @Override
    public String toString() { return name; }

}
