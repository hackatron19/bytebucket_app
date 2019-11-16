package com.bytebucket.medico.models;

public class PillReminder {

    String pillName, pillDosage;
    int pillHour, pillMin, pillId, pillFreq;

    public PillReminder() {
    }

    public PillReminder(String pillName, String pillDosage, int pillHour, int pillMin, int pillId, int pillFreq) {
        this.pillName = pillName;
        this.pillDosage = pillDosage;
        this.pillHour = pillHour;
        this.pillMin = pillMin;
        this.pillId = pillId;
        this.pillFreq = pillFreq;
    }

    public int getPillFreq() {
        return pillFreq;
    }

    public void setPillFreq(int pillFreq) {
        this.pillFreq = pillFreq;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getPillDosage() {
        return pillDosage;
    }

    public void setPillDosage(String pillDosage) {
        this.pillDosage = pillDosage;
    }

    public int getPillHour() {
        return pillHour;
    }

    public void setPillHour(int pillHour) {
        this.pillHour = pillHour;
    }

    public int getPillMin() {
        return pillMin;
    }

    public void setPillMin(int pillMin) {
        this.pillMin = pillMin;
    }

    public int getPillId() {
        return pillId;
    }

    public void setPillId(int pillId) {
        this.pillId = pillId;
    }
}
