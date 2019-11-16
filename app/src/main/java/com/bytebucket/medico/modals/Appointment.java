package com.bytebucket.medico.modals;

public class Appointment {

    String problem, date;
    String dfuid, pfuid, appointmentId;
    String status;
    boolean rate;
    int priority;

    public Appointment() {
    }

    public Appointment(String problem, String date, String dfuid, String pfuid, String appointmentId, String status, boolean rate) {
        this.problem = problem;
        this.date = date;
        this.dfuid = dfuid;
        this.pfuid = pfuid;
        this.appointmentId = appointmentId;
        this.status = status;
        this.rate = rate;
        this.priority = -1;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDfuid() {
        return dfuid;
    }

    public void setDfuid(String dfuid) {
        this.dfuid = dfuid;
    }

    public String getPfuid() {
        return pfuid;
    }

    public void setPfuid(String pfuid) {
        this.pfuid = pfuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
