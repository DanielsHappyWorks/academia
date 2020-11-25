package com.danielshappyworks.academia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fistName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String address;

    @ManyToMany(mappedBy = "professors")
    @JsonIgnore
    private Set<Course> courses;

    public Professor() {
    }

    public Professor(String fistName, String lastName, String email, String address) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id) &&
                Objects.equals(fistName, professor.fistName) &&
                Objects.equals(lastName, professor.lastName) &&
                Objects.equals(email, professor.email) &&
                Objects.equals(address, professor.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fistName, lastName, email, address);
    }
}
