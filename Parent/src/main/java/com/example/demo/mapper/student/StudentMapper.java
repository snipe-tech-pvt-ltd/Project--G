package com.example.demo.mapper.student;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.student.Student;
import com.example.demo.mapper.AbstractModelMapper;
import com.example.demo.model.student.StudentModel;

@Component
public class StudentMapper extends AbstractModelMapper<StudentModel, Student> {

	@Override
	public Class<StudentModel> entityType() {
		return StudentModel.class;
	}

	@Override
	public Class<Student> modelType() {
		return Student.class;
	}
/*
	public static Class<StudentModel> entity() {
		return StudentModel.class;
		
		// TODO Auto-generated method stub
		//return null;
	}

	public static Class<StudentModel> entitytype() {
		return StudentModel.class;
		
		
		// TODO Auto-generated method stub
		//return null;
	}
*/
	
}
