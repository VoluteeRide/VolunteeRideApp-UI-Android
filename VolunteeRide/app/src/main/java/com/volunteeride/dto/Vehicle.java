package com.volunteeride.dto;

/**
 * Created by mthosani on 12/27/15.
 */
public class Vehicle {

    private String make;
    private String model;
    private int totalRiderCapacity;
    private VehicleTypeEnum type;
    private String color;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTotalRiderCapacity() {
        return totalRiderCapacity;
    }

    public void setTotalRiderCapacity(int totalRiderCapacity) {
        this.totalRiderCapacity = totalRiderCapacity;
    }

    public VehicleTypeEnum getType() {
        return type;
    }

    public void setType(VehicleTypeEnum type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
