package com.example.demo.dao.student;

import java.util.List;

import com.example.demo.domain.student.Student;
import com.example.demo.response.Response;

public interface StudentDAO {

	public Response addStudent(Student update)throws Exception;

	public Response updateStudent(Student adm)throws Exception;

	public Student getStudent(String stdId)throws Exception;

	public Response deleteStudent(String stdId)throws Exception;

	public List<Student> allStudent()throws Exception;

	

}
