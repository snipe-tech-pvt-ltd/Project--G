package com.example.demo.domain.parent;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Parent11")
public class Parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2585956794138390018L;

	@Id
	@Column(name="parentId")
	private String parentId;
	
    @Column(name="fullname")
	private String fullname;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="phoneno")
	private String phoneno;
	
	@Column(name="password")
	private String password;
	
	@Column(name="isActive")
	private boolean isActive;
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getfullname() {
		return fullname;
	}

	public   void setfullname(String fullname) {
		this.fullname = fullname;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getphoneno() {
		return phoneno;
	}

	public  void setphoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getparentId() {
		return parentId;
	}

	public   void setparentId(String parentId) {
		this.parentId = parentId;
	}
	public Parent(String parentId, String fullname, String email, String phoneno, String password,
			boolean isActive) {
		super();
		this.parentId = parentId;
		this.fullname = fullname;
		this.email = email;
		this.phoneno = phoneno;
		this.password = password;
		this.isActive = isActive;
	}
	public Parent() {
		super();
	}
	 Parent(String parentId, String fullname, String email, String phoneno, String password) {
			super();
			this.parentId = parentId;
			this.fullname = fullname;
			this.email = email;
			this.phoneno = phoneno;
			this.password = password;
		}

	/* 
	 @OneToOne(mappedBy = "Parent11")
	    @JsonBackReference(value = "Student-reference")
	    public Student getStudent() {
	        return Student;
	    }

	    public void setStudent(Student student) {
	        this.student = student;
	    }
	    */
}


