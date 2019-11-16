package com.bytebucket.medico.modals;

public class Address {
    String city, location, state;

    public Address() {
    }

    public Address(String city, String location, String state) {
        this.city = city;
        this.location = location;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
