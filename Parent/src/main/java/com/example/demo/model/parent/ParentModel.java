
package com.example.demo.model.parent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class ParentModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4587430320826983700L;

	private String parentId;

	private String fullname;
	
    private String email;
    
    private String phoneno;
    
	private String password;
	
    private boolean isActive;
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getfullname() {
		return fullname;
	}

	public void setfullname(String fullname) {
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

	public void setphoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ParentModel() {

	}

    
    public ParentModel(String parentId, String fullname, String email, String phoneno, String password,
			boolean isActive) {
		this.parentId = parentId;
		this.fullname = fullname;
		this.email = email;
		this.phoneno = phoneno;
		this.password = password;
		this.isActive = isActive;
	}

	
	
    
}
