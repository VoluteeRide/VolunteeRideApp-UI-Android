package com.volunteeride.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * Created by mthosani on 12/19/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Center {

        private String name;
        private Location location;
        private List<Location> pickUpLocations;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Location> getPickUpLocations() {
            return pickUpLocations;
        }

        public void setPickUpLocations(List<Location> pickUpLocations) {
            this.pickUpLocations = pickUpLocations;
        }

        @Override
        public String toString() {
            return "Center name ='" + name + '\'' + "\n" +
                    location + "\n" +
                    "Pick Up Locations = " + pickUpLocations +
                    '}';
        }
}
