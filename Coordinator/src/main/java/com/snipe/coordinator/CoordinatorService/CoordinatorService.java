package com.snipe.coordinator.CoordinatorService;

import java.util.List;

import com.snipe.coordinator.Model.CoordinatorModel;
import com.snipe.coordinator.Model.UpdatePassword;
import com.snipe.coordinator.Response.Response;





public interface CoordinatorService 
{
	
	
	public Response addUser(CoordinatorModel com) throws Exception;

	public Response updateUser(CoordinatorModel coordinatorModel) throws Exception;

	public CoordinatorModel getUser(String userId) throws Exception;

	public Response deleteUser(String userId) throws Exception;

	public List<CoordinatorModel> getUsers() throws Exception;
	
	public CoordinatorModel authenticate(CoordinatorModel com) throws Exception;

	public String resetPassword(CoordinatorModel coordinatorModel) throws Exception;

	public String changePassword(UpdatePassword updatePassword);

	public boolean isUserNameExist(String userName)  throws Exception;
	

	public Response updateUserStatus(CoordinatorModel coordinatorModel) throws Exception;
	
	// public List<CoordinatorModel> findAll() throws Exception;
}
