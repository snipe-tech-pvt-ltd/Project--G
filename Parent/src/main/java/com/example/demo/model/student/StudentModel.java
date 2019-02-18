package com.example.demo.model.student;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class StudentModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4018203673652494407L;

	/**
	 * 
	 */
	
	private String stdId;
	
	private String age;
	
	private String firstName;
	
	private String lastName;
	
	private String image;
	
	private String dob;
	
	private String gender;
	
    private String parentName;
	
	//private String motherName;
	
	private String parentOccupation;
	
	
	private String email;
	
	private String mobileNumber;
	
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
	public boolean isActive() {
		return isActive;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setParentName(String parentName) {
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
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
