package com.example.demo.dao.parent;



import java.util.List;

import com.example.demo.domain.parent.Parent;
import com.example.demo.response.Response;

public interface ParentDAO {
	public Response addParent(Parent parent)throws Exception;
	
	public Response deleteParent(String parentId)throws Exception;

	public Parent getParent(String parentId)throws Exception;

	public boolean isParentNameExist(String parentName) throws Exception;


	public List<Parent> getParents()throws Exception;

	public Parent authenticate(Parent parent)throws Exception;

	public Response updateParent(Parent parent)throws Exception;

    public String forgotPassword(String parentId, String encriptString)throws Exception;
	
    public Response updateParentStatus(Parent parent)throws Exception;

    public Parent isParentExist(Parent parent)throws Exception;

	public boolean isfullnameExist(String fullname)throws Exception;

	

}
