package com.study.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "speaker")
@SecondaryTable(name = "users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Speaker {

    @Column(name = "id")
    private int id;
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
    private int rating;
    @Column(name = "bonuses")
    private int bonuses;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
    public int hashCode(){
        return Objects.hash(login, password, firstName, lastName, email, role);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj instanceof Speaker){
            final Speaker other = (Speaker) obj;
            return Objects.deepEquals(role, other.getRole())
                    && Objects.equals(login, other.getLogin())
                    && Objects.equals(password,other.getPassword())
                    && Objects.equals(firstName, other.getFirstName())
                    && Objects.equals(lastName, other.getLastName())
                    && Objects.equals(email, other.getEmail())
                    && rating == other.getRating()
                    && bonuses == bonuses;
        } else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
