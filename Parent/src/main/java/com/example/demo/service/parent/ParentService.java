
package com.example.demo.service.parent;

import java.util.List;

import com.example.demo.model.parent.ParentModel;
import com.example.demo.response.Response;


public interface ParentService {

	public Response addParent(ParentModel parent) throws Exception;
	
	public Response updateParent(ParentModel parentModel) throws Exception;

	public ParentModel getParent(String parentId) throws Exception;

	public boolean isParentNameExist(String userName) throws Exception;

	public Response deleteParent(String parentId) throws Exception;

	public List<ParentModel> getParents() throws Exception;
	
	public ParentModel authenticate(ParentModel parent) throws Exception;

	public String forgotPassword(ParentModel parentModel) throws Exception;

	public boolean isfullnameExist(String fullname)  throws Exception;
	
	public Response updateParentStatus(ParentModel parentModel) throws Exception;

}
