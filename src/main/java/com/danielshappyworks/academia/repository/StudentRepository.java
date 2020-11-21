package com.danielshappyworks.academia.repository;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);
    List<Student> findByLastName(String lastName);
}