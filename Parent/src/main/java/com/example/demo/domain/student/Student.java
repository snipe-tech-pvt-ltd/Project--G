package com.example.demo.domain.student;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.response.Response;
@Entity
@Table(name="admission")
public class Student implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5982903528882829296L;

	@Id
	private String stdId;
	
	private String firstName;
	
	private String lastName;
	
	private String image;
	
	private String dob;
	
	private String age;
	
	private String gender;
	
	private String parentName;
	
	//private String motherName;
	
	private String parentOccupation;
	
	private String email;
	
	private String mobileNumber;
	
	//private String fatherOfficeAddress;
	
	private String address;
	
	private String city;
	
	private String pinCode;
	
	private String state;
	
	private String country;
	
	//private String coursesApplyied;
	
	private boolean isActive;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStdId() {
		return stdId;
	}

	public String getFirstName() {
		return firstName;
	}


	public String getParentName() {
		return parentName;
	}
	
	

	public String getParentOccupation() {
		return parentOccupation;
	}
	
	public String getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
/*
	public String getFatherOfficeAddress() {
		return FatherOfficeAddress;
	
	*/
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}
/*
	public String getCoursesApplyied() {
		return coursesApplyied;
	}
*/
	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setparentName(String parentName) {
		this.parentName = parentName;
	}

	
	public void setParentOccupation(String parentOccupation) {
		this.parentOccupation = parentOccupation;
	}

	
	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
/*
	public void setFatherOfficeAddress(String fatherOfficeAddress) {
		this.fatherOfficeAddress = fatherOfficeAddress;
	}
*/
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}
/*
	public void setCoursesApplyied(String coursesApplyied) {
		this.coursesApplyied = coursesApplyied;
	}
*/
	public Response updateStudent(Student adm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student getStudent(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response deleteStudent(String stdId2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> allStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	public Response addStudent(Student update) {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

	

		




