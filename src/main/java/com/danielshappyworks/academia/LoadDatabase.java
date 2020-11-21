package com.danielshappyworks.academia;

import com.danielshappyworks.academia.entity.Course;
import com.danielshappyworks.academia.entity.Professor;
import com.danielshappyworks.academia.entity.Student;
import com.danielshappyworks.academia.repository.CourseRepository;
import com.danielshappyworks.academia.repository.ProfessorRepository;
import com.danielshappyworks.academia.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {

        return args -> {
            //create professors
            Professor profA = new Professor("Daniel", "Foth", "daniel.foth@college.edu", "Westmeath, Ireland");
            Professor profB = new Professor("Declan", "Cordial", "Declan.Cordial@college.edu", "Yellow Submarine, Ireland");
            Professor profC = new Professor("Avril", "Lavigne", "Avril.Lavigne@college.edu", "Dublin, Ireland");
            professorRepository.saveAll(new ArrayList<Professor>(Arrays.asList(profA, profB, profC)));

            //create courses
            Course courseA = new Course("Bachelor of Business (Hons) Business and Law", "This course combines both law and business into a single degree programme and provides you with a solid grounding in both disciplines.", new HashSet<Professor>(Arrays.asList(profA)));
            Course courseB = new Course("Certificate in Emergency Nursing", "This programme is designed to give solid theoretical and practical base to the understanding and development of emergency nursing care, providing a core", new HashSet<Professor>(Arrays.asList(profB, profC)));
            Course courseC = new Course("Bachelor of Arts (Hons) in Design in Digital Media", "This programme aims to provide structured yet dynamic learning that is both adaptable and responsive to the successful formation of design professionals in this constantly changing environment.");
            Course courseD = new Course("Accounting", "This hugely-respected honours degree in accounting will provide you with an in-depth knowledge of the fundamental theories, concepts, principles and practices essential for a rewarding career in professional accountancy.");
            courseRepository.saveAll(new ArrayList<Course>(Arrays.asList(courseA, courseB, courseC, courseD)));

            //create students
            HashSet<Student> students = new HashSet<>();
            Student studentA = new Student("A", "A", "AA@email", "address", new HashSet<Course>(Arrays.asList(courseA)));
            Student studentB = new Student("B", "B", "BB@email", "address", new HashSet<Course>(Arrays.asList(courseA, courseB)));
            Student studentC = new Student("C", "C", "CC@email", "address", new HashSet<Course>(Arrays.asList(courseA, courseD)));
            Student studentD = new Student("D", "D", "DD@email", "address", new HashSet<Course>(Arrays.asList(courseA)));
            Student studentE = new Student("E", "E", "EE@email", "address", new HashSet<Course>(Arrays.asList(courseA, courseB, courseD)));
            Student studentF = new Student("F", "F", "FF@email", "address");
            studentRepository.saveAll(new ArrayList<Student>(Arrays.asList(studentA, studentB, studentC, studentD, studentE, studentF)));

            courseRepository.findAll().forEach(course -> log.info("Preloaded " + course));
        };
    }
}