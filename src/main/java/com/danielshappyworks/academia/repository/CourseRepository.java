package com.danielshappyworks.academia.repository;

import com.danielshappyworks.academia.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    Course findById(long id);
}