package com.danielshappyworks.academia.api;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.exception.NotFoundException;
import com.danielshappyworks.academia.entity.Student;
import com.danielshappyworks.academia.repository.CourseRepository;
import com.danielshappyworks.academia.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/students")
    List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/id/{id}")
    Student getByID(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("student", id));
    }

    @GetMapping("/students/name/{name}")
    List<Student> getByName(@PathVariable String name) {
        return studentRepository.findByLastName(name);
    }

    @PostMapping("/students")
    Student postCourse(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    Student putCourse(@PathVariable Long id, @RequestBody Student newStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFistName(newStudent.getFistName());
                    student.setLastName(newStudent.getLastName());
                    student.setAddress(newStudent.getAddress());
                    student.setEmail(newStudent.getEmail());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    //prevent from persisting courses
                    newStudent.setCourses(new HashSet<>());
                    return studentRepository.save(newStudent);
                });
    }

    @DeleteMapping("/students/{id}")
    void deleteCourse(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @GetMapping("/students/register/{student_id}/{course_id}")
    Course registerForCourse(@PathVariable Long student_id, @PathVariable Long course_id) {
        return studentRepository.findById(student_id)
                .map(student -> {
                    return courseRepository.findById(course_id).map(course -> {
                        course.registerStudent(student);
                        return courseRepository.save(course);
                    }).orElseThrow(() -> new NotFoundException("course", course_id));
                }).orElseThrow(() -> new NotFoundException("student", student_id));
    }
}