package com.study.persistence.entity;

import java.time.LocalTime;

public class Report {
    private Speaker speaker;
    private int id;
    private String title;
    private LocalTime timeStart;
    private int registered;
    private int attended;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }

    public int getRegistered() {
        return registered;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getAttended() {
        return attended;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Speaker getSpeaker() {
        return speaker;
    }
}
