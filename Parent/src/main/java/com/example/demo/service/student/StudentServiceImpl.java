package com.example.demo.service.student;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.student.StudentController;
import com.example.demo.dao.student.StudentDAO;
import com.example.demo.domain.student.Student;
import com.example.demo.mapper.student.StudentMapper;
import com.example.demo.model.student.StudentModel;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.SmtpMailSender;
import com.example.demo.dao.student.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentMapper studentMapper;
	/*
	@Autowired
	StudentController studentController;
	*/

	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Override
	public Response addStudent(StudentModel model) throws Exception {
		Student update=new Student();
		
		try {
						
            	BeanUtils.copyProperties(model, update);
	            update.setStdId(CommonUtils.generateRandomId());
	           /* student.setFirstName(studentmodel.getFirstName());
	            student.setLastName(studentmodel.getLastName());*/
	           
                update.setActive(true);
              
                Response response = studentDAO.addStudent(update);
    			return response;
    		}
    		catch (Exception ex) {
    			logger.info("Exception Service:" + ex.getMessage());
    		}
    		return null;
    }
	@Override
	public Response updateStudent(StudentModel studentModel) throws Exception {
		try {
			Student adm = new Student();
			BeanUtils.copyProperties(studentModel, adm);
			Response response = studentDAO.updateStudent(adm);
			return response;
		}
		catch(Exception e) {
			logger.info("Exception in update Admission"+e.getMessage());
		}
		return null;
	}
	@Override
	public StudentModel getStudent(String studentId) throws Exception {
		try {
			
			Student admission = studentDAO.getStudent(studentId);
			StudentModel studentModel = new StudentModel();
			if (admission == null)
				return null;
			BeanUtils.copyProperties(studentModel, studentModel);
			return studentModel;
		} catch (Exception e) {
			logger.info("Exception getStudent:", e.getMessage());
			return null;
		}
	}


	@Override
	public Response deleteStudent(String stdId) throws Exception {
		try {
			return studentDAO.deleteStudent(stdId);
		}
		
		catch(Exception e ) {
			logger.info("Exception in delete Admission"+e.getMessage() );
			return null;
			
		}
	}

	@Override
	public List<StudentModel> allStudent() throws Exception {
		try {
			List<Student> student=studentDAO.allStudent();
			return studentMapper.entityList(student);
			
		}
		catch(Exception e) {logger.info("Exception getadmission:", e);
		
		}
		return null;
	}
	
	
}
