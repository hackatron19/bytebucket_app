package com.bytebucket.medico.modals;

public class Doctor {
    Address address;
    int count, rating;

    String opening_time;
    int average_time;
    String mobile, name, pdf, speciality, fuid;
    boolean verified;

    public Doctor() {
    }

    public Doctor(Address address, int count, int rating, String opening_time, int average_time, String mobile, String name, String pdf, String speciality, String fuid, boolean verified) {
        this.address = address;
        this.count = count;
        this.rating = rating;
        this.opening_time = opening_time;
        this.average_time = average_time;
        this.mobile = mobile;
        this.name = name;
        this.pdf = pdf;
        this.speciality = speciality;
        this.fuid = fuid;
        this.verified = verified;
    }

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public int getAverage_time() {
        return average_time;
    }

    public void setAverage_time(int average_time) {
        this.average_time = average_time;
    }
}
