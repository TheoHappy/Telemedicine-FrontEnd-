package com.example.frontend.Model;

import com.google.gson.annotations.SerializedName;

public class Doctor {
    @SerializedName("DocId")
    private int docId;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Specs")
    private String specs;
    @SerializedName("Address")
    private String address;
    @SerializedName("Stars")
    private double stars;

    private String token;


    public Doctor(String fullName, String specs, String address,double stars) {
        this.fullName = fullName;
        this.specs = specs;
        this.address = address;
        this.stars = stars;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getToken() {
        return token;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "FullName='" + fullName + '\'' +
                ", specs='" + specs + '\'' +
                ", address='" + address + '\'' +
                ", stars=" + stars +
                '}';
    }
}
