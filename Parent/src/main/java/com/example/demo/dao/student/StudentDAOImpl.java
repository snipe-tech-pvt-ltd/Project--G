package com.example.demo.dao.student;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.constants.StatusCode;
import com.example.demo.domain.student.Student;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO{
private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Response addStudent(Student student) throws Exception {
		Response response = CommonUtils.getResponseObject("Add Admission Data");
		try {
			entityManager.persist(student);
			response.setStatus(StatusCode.SUCCESS.name());
			response.setMessage(" Admission Added Successfully");
		} catch (Exception e) {
			logger.error("Exception in adding Admission", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
			response.setMessage("Failed to add Admission ");
		}
		return  response;
	}
	@Override
	public Response updateStudent(Student model) throws Exception {
		Response response = CommonUtils.getResponseObject("Update Admission data");
		try {
			Student admission = getStudent(model.getStdId());
			admission.setAge(model.getAge());
			admission.setFirstName(model.getFirstName());
			admission.setLastName(model.getLastName());
			admission.setImage(model.getImage());
			admission.setDob(model.getDob());
			admission.setGender(model.getGender());
			admission.setEmail(model.getEmail());
			admission.setMobileNumber(model.getMobileNumber());
			admission.setparentName(model.getParentName());
			admission.setParentOccupation(model.getParentOccupation());
			admission.setAddress(model.getAddress());
			admission.setCity(model.getCity());
			admission.setPinCode(model.getPinCode());
			admission.setState(model.getState());
			admission.setCountry(model.getCountry());
			//admission.setCoursesApplyied(model.getCoursesApplyied());
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in updateAdmission", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  response;
	}

	@Override
	public Student getStudent(String stdId) throws Exception {
		try {
			String hql = "FROM Student where stdId=?1 and isActive=true";
			return (Student) entityManager.createQuery(hql).setParameter(1, stdId).getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception in getStudent", e);
			return null;
		}
	}


	@Override
	public Response deleteStudent(String stdId) throws Exception {
		Response response = CommonUtils.getResponseObject("Delete Admission data");
		try {
			Student admission = getStudent(stdId);
			admission.setActive(false);
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.info("Exception in delete admission", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
		}
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> allStudent() throws Exception {
		try {
			String hql ="from Student where isActive=true";
			return (List<Student>) entityManager.createQuery(hql).getResultList();
		}
		catch(HibernateException e) {logger.info("Exception in allStudent" +e.getMessage());
		return null;
		}
	}


}
