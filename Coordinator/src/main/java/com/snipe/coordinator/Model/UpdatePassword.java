package com.snipe.coordinator.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class UpdatePassword 
{
	private String userId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public String getuserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getoldPassword() {
		return oldPassword;
	}
	public void setoldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getnewPassword() {
		return newPassword;
	}
	public void setnewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getconfirmPassword() {
		return confirmPassword;
	}
	public void setconfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

}
