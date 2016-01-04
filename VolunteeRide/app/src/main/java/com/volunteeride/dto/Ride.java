package com.volunteeride.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.volunteeride.jackson.CustomDateDeserializer;

import org.joda.time.DateTime;

import java.util.List;


/**
 * Created by ayazlakdawala on 12/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ride extends BaseDTOObject {

    private String volunteerId;
    private List<String> rideSeekerIds;
    private Location pickupLoc;
    private Location dropoffLoc;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private DateTime pickupTime;
    private String status;
    private String centerId;
    private int totalNoOfRiders;
    private List<String> nextRideUserOperations;

    public Ride() {
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public List<String> getRideSeekerIds() {
        return rideSeekerIds;
    }

    public void setRideSeekerIds(List<String> rideSeekerIds) {
        this.rideSeekerIds = rideSeekerIds;
    }

    public Location getPickupLoc() {
        return pickupLoc;
    }

    public void setPickupLoc(Location pickupLoc) {
        this.pickupLoc = pickupLoc;
    }

    public Location getDropoffLoc() {
        return dropoffLoc;
    }

    public void setDropoffLoc(Location dropoffLoc) {
        this.dropoffLoc = dropoffLoc;
    }

    public DateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(DateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public int getTotalNoOfRiders() {
        return totalNoOfRiders;
    }

    public void setTotalNoOfRiders(int totalNoOfRiders) {
        this.totalNoOfRiders = totalNoOfRiders;
    }

    public List<String> getNextRideUserOperations() {
        return nextRideUserOperations;
    }

    public void setNextRideUserOperations(List<String> nextRideUserOperations) {
        this.nextRideUserOperations = nextRideUserOperations;
    }

}
