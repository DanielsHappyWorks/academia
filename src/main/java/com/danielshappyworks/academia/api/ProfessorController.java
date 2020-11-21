package com.danielshappyworks.academia.api;

import com.danielshappyworks.academia.entity.Professor;
import com.danielshappyworks.academia.entity.Student;
import com.danielshappyworks.academia.repository.ProfessorRepository;
import com.danielshappyworks.academia.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ProfessorController {

    private final ProfessorRepository repository;

    ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/professors")
    List<Professor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/professors/{id}")
    Professor getByID(@PathVariable Long id) {
        return repository.findById(id).orElse( null);
    }

    @GetMapping("/professors/{name}")
    List<Professor> getByID(@PathVariable String name) {
        return repository.findByLastName(name);
    }

    @PostMapping("/professors")
    Professor postCourse(@RequestBody Professor professor) {
        return repository.save(professor);
    }

    @PutMapping("/professors")
    Professor putCourse(@RequestBody Professor professor) {
        return repository.save(professor);
    }

    @DeleteMapping("/professors/{id}")
    void deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
    }
}