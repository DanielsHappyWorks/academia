package com.danielshappyworks.academia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Set<Professor> professors;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "courses")
    private Set<Student> students;

    public Course() {
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.professors = null;
        this.students = null;
    }

    public Course(String name, String description, Set<Professor> professors) {
        this.name = name;
        this.description = description;
        this.professors = null;
        this.students = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setProfessor(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<Professor> getProfessor() {
        return professors;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", professors=" + professors.toString() +
                ", students=" + students.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(professors, course.professors) &&
                Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, professors, students);
    }
}
