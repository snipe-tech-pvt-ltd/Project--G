package com.snipe.coordinator.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_DEFAULT)
public class CoordinatorModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585956794138390018L;

	private String coId;
	
	private String userName;
	
	private String firstName;
    
	private String lastName;
	
private String password;
    
	private String email;

    private boolean isActive;
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}





	public CoordinatorModel(String coId, String userName, String firstName, String lastName, String password,
			String email, boolean isActive) {
		super();
		this.coId = coId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
	}





	


public CoordinatorModel() {
	super();

}



public boolean getIsActive() {
		return isActive;
	}




	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

    public String getemail() {
		return email;
	}





	public void setemail(String email) {
		this.email = email;
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





	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}





	public String getlastName() {
		return lastName;
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


	
}
