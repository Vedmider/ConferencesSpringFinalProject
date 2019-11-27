package com.study.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "rights")
public class Right {

    @Column(name = "id")
    private int id;
    @Column(name = "right_title")
    private String  title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Right)) {
            return false;
        }
        Right right = (Right) obj;

        return id == right.getId() && (title == null ? right.getTitle() == null : title.equals(right.getTitle()));
    }

    @Override
    public int hashCode() {
        int result = 10;
        result = 31 * result + id + (title == null ? 0 : title.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ID " + id + ", title " + title;
    }
}
