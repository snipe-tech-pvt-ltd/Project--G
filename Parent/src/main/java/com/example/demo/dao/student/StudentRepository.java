package com.example.demo.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.student.Student;



public interface StudentRepository extends JpaRepository<Student, String>{

}
