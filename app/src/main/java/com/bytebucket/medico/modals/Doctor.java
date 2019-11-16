package com.bytebucket.medico.modals;

public class Doctor {
    Address address;
    int count, rating;

    String mobile, name, pdf, speciality, fuid;
    boolean verified;

    public Doctor() {
    }

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public Doctor(Address address, int count, int rating, String mobile, String name, String pdf, String speciality, String fuid, boolean verified) {
        this.address = address;
        this.count = count;
        this.rating = rating;
        this.mobile = mobile;
        this.name = name;
        this.pdf = pdf;
        this.speciality = speciality;
        this.fuid = fuid;
        this.verified = verified;
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
}
