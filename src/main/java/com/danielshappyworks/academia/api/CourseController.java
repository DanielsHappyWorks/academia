package com.danielshappyworks.academia.api;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.exception.NotFoundException;
import com.danielshappyworks.academia.repository.CourseRepository;
import com.danielshappyworks.academia.repository.ProfessorRepository;
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
    private final ProfessorRepository profRepository;

    CourseController(CourseRepository repository, ProfessorRepository profRepository) {
        this.repository = repository;
        this.profRepository = profRepository;
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

    @GetMapping("/courses/assign/{prof_id}/{course_id}")
    Course registerForCourse(@PathVariable Long prof_id, @PathVariable Long course_id) {
        return profRepository.findById(prof_id)
                .map(prof -> {
                    return repository.findById(course_id).map(course -> {
                        course.assignProfessor(prof);
                        return repository.save(course);
                    }).orElseThrow(() -> new NotFoundException("course", course_id));
                }).orElseThrow(() -> new NotFoundException("professor", prof_id));
    }
}