package com.study.persistence.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "speaker")
@SecondaryTable(name = "users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(table = "users", name = "login")
    private String login;
    @Column(table = "users", name = "user_password")
    private String password;
    @Column(table = "users", name = "first_name")
    private String firstName;
    @Column(table = "users", name = "last_name")
    private String lastName;
    @Column(table = "users", name = "email")
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(table = "users", name = "user_role", referencedColumnName = "id")
    private Role role;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "bonuses")
    private Integer bonuses;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getBonuses() {
        return bonuses;
    }

    public void setBonuses(Integer bonuses) {
        this.bonuses = bonuses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(Hibernate.getClass(o))) return false;

        Speaker that = (Speaker) o;

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
