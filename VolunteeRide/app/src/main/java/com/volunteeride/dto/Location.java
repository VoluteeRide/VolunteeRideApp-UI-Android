package com.volunteeride.dto;

/**
 * Created by mthosani on 12/19/15.
 */
public class Location {

    private String city;
    private String state;
    private String streetAddress;
    private String zipcode;
    private int houseNumber;
    private String locationName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return " city =" + city + '\n' +
                "state =" + state +  '\n' +
                "streetAddress =" + streetAddress +  '\n' +
                "zipcode =" + zipcode + '\n' +
                "houseNumber =" + houseNumber;
    }
}
