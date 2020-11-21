package com.danielshappyworks.academia.api;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.repository.CourseRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class CourseController {

    private final CourseRepository repository;

    CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courses")
    List<Course> getAll() {
        return repository.findAll();
    }

    @GetMapping("/courses/{id}")
    Course getByID(@PathVariable Long id) {
        return repository.findById(id).orElse( null);
    }

    @GetMapping("/courses/{name}")
    List<Course> getByID(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/courses")
    Course postCourse(@RequestBody Course course) {
        return repository.save(course);
    }

    @PutMapping("/courses")
    Course putCourse(@RequestBody Course course) {
        return repository.save(course);
    }

    @DeleteMapping("/courses/{id}")
    void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}