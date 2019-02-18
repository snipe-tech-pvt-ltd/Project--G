package com.example.demo.service.student;

import java.util.List;

import com.example.demo.model.student.StudentModel;
import com.example.demo.response.Response;

public interface StudentService {

	public Response addStudent(StudentModel model)throws Exception;

	public Response updateStudent(StudentModel studentModel)throws Exception;

	public StudentModel getStudent(String stdId)throws Exception;

	public Response deleteStudent(String stdId)throws Exception;

	public List<StudentModel> allStudent()throws Exception;

	

}
