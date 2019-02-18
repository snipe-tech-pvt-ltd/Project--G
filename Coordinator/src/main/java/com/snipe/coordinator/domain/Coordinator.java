package com.snipe.coordinator.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Coordinator")

public class Coordinator  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585956794138390018L;
	
	@Id
	@Column(name="coId")
	private String coId;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="isActive")
	private boolean isActive;
	
	
	 public Coordinator()
		{
			super();
		}




		public String getcoId() {
			return coId;
		}




		public void setcoId(String coId) {
			this.coId = coId;
		}




		public String getuserName() {
			return userName;
		}




		public void setuserName(String userName) {
			this.userName = userName;
		}




		public String getfirstName() {
			return firstName;
		}




		/**
		 * @param firstName
		 */
		public void setfirstName(String firstName) {
			this.firstName = firstName;
		}




		



		public String getlastName() {
			return lastName;
		}




		


		public String getemail() {
			return email;
		}




		public void setemail(String email) {
			this.email = email;
		}




		




	Coordinator(String coId, String userName, String firstName, String lastName, String password, 
				String email,  boolean isActive) {
			super();
			this.coId = coId;
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;

			this.email = email;
			
			
			this.isActive = isActive;
		
		}

	   public boolean isActive() {
		return isActive;
	}




	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}




	public void setlastName(String lastName) {
			this.lastName = lastName;
		}




		public String getpassword() {
			return password;
		}




		public void setpassword(String password) {
			this.password = password;
		}




		public static long getSerialversionuid() {
			return serialVersionUID;
		}




		public Coordinator(String coId, String userName, String firstName, String lastName, String password) {
			super();
			this.coId = coId;
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
		}




			
		


		
	}


