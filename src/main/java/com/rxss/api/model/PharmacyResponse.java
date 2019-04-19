package com.rxss.api.model;

public class PharmacyResponse {

    private String name;
    private String address;
    private Double distance;

    public PharmacyResponse(String name, String address, Double distance) {
        this.name = name;
        this.address = address;
        this.distance = distance;
    }

    public PharmacyResponse() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "PharmacyResponse{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", distance=" + distance +
                '}';
    }
}
