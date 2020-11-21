package com.danielshappyworks.academia.repository;

import com.danielshappyworks.academia.entity.Professor;
import com.danielshappyworks.academia.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findById(long id);
    List<Professor> findByLastName(String lastName);
}