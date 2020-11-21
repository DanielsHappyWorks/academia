package com.danielshappyworks.academia.api;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.entity.Student;
import com.danielshappyworks.academia.repository.CourseRepository;
import com.danielshappyworks.academia.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class StudentController {

    private final StudentRepository repository;

    StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    Student getByID(@PathVariable Long id) {
        return repository.findById(id).orElse( null);
    }

    @GetMapping("/students/{name}")
    List<Student> getByID(@PathVariable String name) {
        return repository.findByLastName(name);
    }

    @PostMapping("/students")
    Student postCourse(@RequestBody Student student) {
        return repository.save(student);
    }

    @PutMapping("/students")
    Student putCourse(@RequestBody Student student) {
        return repository.save(student);
    }

    @DeleteMapping("/students/{id}")
    void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}