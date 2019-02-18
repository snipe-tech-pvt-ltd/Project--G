
package com.example.demo.service.parent;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.StatusCode;
import com.example.demo.dao.parent.ParentDAO;
import com.example.demo.domain.parent.Parent;
import com.example.demo.mapper.parent.ParentMapper;
import com.example.demo.model.parent.ParentModel;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.SmtpMailSender;


@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	ParentDAO parentDAO;
	
	
	@Autowired
	ParentMapper parentMapper;
	
	@Autowired
	SmtpMailSender smtpMailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);

	@Override
	public Response addParent(ParentModel parentModel) throws Exception 
	{
		try
		{
			Parent parent= new Parent();
			parent.setparentId(CommonUtils.generateRandomId());
			parent.setfullname(parentModel.getfullname());
			parent.setphoneno(parentModel.getphoneno());
			parent.setemail(parentModel.getemail());
		    parent.setpassword(CommonUtils.encriptString(parentModel.getpassword()));
		    parent.setActive(true);
			
		    
		    
		    Response response = parentDAO.addParent(parent);
			return response;
		}
		catch(Exception e)
		{
			logger.info("Exception Service:" + e.getMessage());
			Response response=new Response();
			response.setStatus(StatusCode.ERROR.name());
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@Override
	public Response updateParent(ParentModel parentModel) throws Exception {
		
		{
			Parent parent=new Parent();
			BeanUtils.copyProperties(parentModel, parent);
			Response res=parentDAO.updateParent(parent);
			return res;
		}
	}
	/*--------------------------------------------get Parent by ID-----------------------------------------*/
	
	
		@Override
		public  ParentModel getParent(String parentId) throws Exception {
			try 
			{
				ParentModel parentModel = new ParentModel();
				Parent parent = parentDAO.getParent(parentId);
				BeanUtils.copyProperties(parent, parentModel);
				return parentModel;
			} 
			catch(Exception e) 
			{
				logger.info("Exception getParent:", e);
				return null;
			}
		}

	
		
	@Override
	public boolean isParentNameExist(String userName) throws Exception {
		try {
			return parentDAO.isParentNameExist(userName);
		} catch (Exception e) {
			logger.info("Exception isUserNameExist:", e);
			return false;
		}
	}

	

	@Override
	public Response deleteParent(String parentId) throws Exception {
		try
		{
			return parentDAO.deleteParent(parentId);
		} 
		catch (Exception e) 
		{
			logger.info("Exception deleteParent:", e);
			return null;
	}
	}
	

	
	@Override
	public List<ParentModel> getParents() throws Exception {
		try
		{
			List<Parent> parents = parentDAO.getParents();
			return parentMapper.entityList(parents);
		} 
		catch (Exception ex)
		{
			logger.info("Exception getParents:", ex);
		}
		return null;
	}

	@Override
	public ParentModel authenticate(ParentModel parentModel) throws Exception {
		parentModel.setpassword(CommonUtils.encriptString(parentModel.getpassword()));
		Parent parent = new Parent();
		BeanUtils.copyProperties(parentModel, parent);
		System.out.println(parentModel.getemail());
		System.out.println(parentModel.getphoneno());

		parent = parentDAO.authenticate(parent);
		if (parent == null)
			return null;
		BeanUtils.copyProperties(parent, parentModel);
		return parentModel;
	}

@SuppressWarnings("unused")
	@Override
	public String forgotPassword(ParentModel parentModel) throws Exception {
		
		try {
			Parent parent = new Parent();
			BeanUtils.copyProperties(parentModel, parent);
			parent = parentDAO.isParentExist(parent);
			System.out.println(parent.getfullname());
			if (parent != null) {
				String password = CommonUtils.generateRandomId();
				//String password = "kalal";
				
				String status = parentDAO.forgotPassword(parent.getparentId(), CommonUtils.encriptString(password));
				if (status.equals(StatusCode.SUCCESS.name())) {
					String email=parent.getemail();
					String pass=password;
					smtpMailSender.send(email,"Snipe It tech pvt ltd reset password","forgot password ="+pass);
				}
				return status;
			} else
				return StatusCode.ERROR.name(); 
		} catch (Exception e) {
			logger.error("Exception in forgotPassword:", e);
			return StatusCode.ERROR.name();
		}
	
}

@Override
public boolean isfullnameExist(String fullname) throws Exception {
 
	try {
		return parentDAO.isfullnameExist(fullname);
	} catch (Exception e) {
		logger.info("Exception isfullnameExist:", e);
	return false;
}
}
@Override
public Response updateParentStatus(ParentModel parentModel) throws Exception {
	try {
		Parent parent = new Parent();
		BeanUtils.copyProperties(parentModel, parent);
		Response response = parentDAO.updateParentStatus(parent);
		return response;
	} catch (Exception ex) {
		logger.info("Exception in updateParentStatus:" + ex.getMessage());
	}
	
	return null;
}

}




