package com.study.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Conference {
    private int id;
    private String theme;
    private LocalDateTime plannedDateTime;
    private LocalDateTime happenedDateTime;
    private String address;
    private List<Report> reports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDateTime getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(LocalDateTime plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public LocalDateTime getHappenedDateTime() {
        return happenedDateTime;
    }

    public void setHappenedDateTime(LocalDateTime happenedDateTime) {
        this.happenedDateTime = happenedDateTime;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, theme, address, happenedDateTime, plannedDateTime, reports);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj instanceof Conference){
            final Conference other = (Conference) obj;
            return Objects.deepEquals(reports, other.getId())
                    && id == other.getId()
                    && Objects.equals(theme,other.getTheme())
                    && Objects.equals(plannedDateTime, other.getPlannedDateTime())
                    && Objects.equals(happenedDateTime, other.getHappenedDateTime())
                    && Objects.equals(address, other.getAddress());
        } else{
            return false;
        }
    }
}
