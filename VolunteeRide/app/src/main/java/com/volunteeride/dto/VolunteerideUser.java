package com.volunteeride.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by mthosani on 12/27/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolunteerideUser {

    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Vehicle> ownedVehicles;
    private String centerId;

    private List<UserRoleEnum> userRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(List<Vehicle> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public List<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleEnum> userRoles) {
        this.userRoles = userRoles;
    }
}
