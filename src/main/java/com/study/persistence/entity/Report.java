package com.study.persistence.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speaker_id", referencedColumnName = "id")
    private Speaker speaker;
    @Column(name = "title")
    private String title;
    @Column(name = "time_start")
    private LocalTime timeStart;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    private Conference conference;
    private Integer registered;
    private Integer attended;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public Integer getRegistered() {
        return registered;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(Hibernate.getClass(o))) return false;

        Report that = (Report) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: $s", getClass().getName(), getId());
    }
}
