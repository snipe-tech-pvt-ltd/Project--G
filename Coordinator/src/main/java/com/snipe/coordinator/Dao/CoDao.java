package com.snipe.coordinator.Dao;

import java.util.List;

import com.snipe.coordinator.domain.*;
import com.snipe.coordinator.Response.*;




public interface CoDao {

	public Response addUser(Coordinator co)throws Exception;

	public Response deleteUser(String coId)throws Exception;

	public Coordinator getUser(String coId)throws Exception;

	public List<Coordinator> getUsers()throws Exception;

	public Coordinator authenticate(Coordinator co)throws Exception;

	public Response updateUser(Coordinator co)throws Exception;

	public String resetPassword(String coId, String encriptString)throws Exception;

	public String changePassword(String coId, String confirmpassword, String encriptString)throws Exception;

	public Coordinator isUserExist(Coordinator co)throws Exception;

	public boolean isUserNameExist(String userName)throws Exception;

	public List<Coordinator> getUsersByRole(String role)throws Exception;

	public Response updateUserStatus(Coordinator co)throws Exception;

	List<Coordinator> userAll() throws Exception;

}
