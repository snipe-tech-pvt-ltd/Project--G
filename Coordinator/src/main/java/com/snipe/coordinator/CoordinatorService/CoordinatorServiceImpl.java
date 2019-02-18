package com.snipe.coordinator.CoordinatorService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.snipe.coordinator.Constants.StatusCode;
import com.snipe.coordinator.Dao.CoDao;
import com.snipe.coordinator.domain.Coordinator;
import com.snipe.coordinator.mapper.Coordinator.CoordinatorMapper;
import com.snipe.coordinator.Model.UpdatePassword;
import com.snipe.coordinator.Model.CoordinatorModel;
import com.snipe.coordinator.Response.Response;
import com.snipe.coordinator.Utils.CommonUtils;
import com.snipe.coordinator.Utils.SmtpMailSender;




@Service
public class CoordinatorServiceImpl implements CoordinatorService
{
	@Autowired
	CoDao coDao;
	
	
	@Autowired
	CoordinatorMapper coordinatorMapper;
	
	@Autowired
	SmtpMailSender smtpMailSender;

	private static final Logger logger = LoggerFactory.getLogger(CoordinatorServiceImpl.class);


	@Override
	public Response addUser(CoordinatorModel coordinatorModel) throws Exception 
	{
		try
		{
			Coordinator co= new Coordinator();
			co.setcoId(CommonUtils.generateRandomId());
			co.setuserName(coordinatorModel.getuserName());
			
	
			
			co.setpassword(CommonUtils.encriptString(coordinatorModel.getpassword()));
			co.setfirstName(coordinatorModel.getfirstName());
			co.setlastName(coordinatorModel.getlastName());
			co.setemail(coordinatorModel.getemail());
			
			
			co.setActive(true);
			Response response = coDao.addUser(co);
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
	public Response updateUser(CoordinatorModel coordinatorModel) throws Exception
	{
		Coordinator co=new Coordinator();
		BeanUtils.copyProperties(coordinatorModel, co);
		Response res=coDao.updateUser(co);
		return res;
	}


	@Override
	public CoordinatorModel getUser(String coId) throws Exception {
		try 
		{
			CoordinatorModel coordinatorModel = new CoordinatorModel();
			Coordinator co = coDao.getUser(coId);
			BeanUtils.copyProperties(co, coordinatorModel);
			return coordinatorModel;
		} 
		catch(Exception e) 
		{
			logger.info("Exception getUser:", e);
			return null;
		}
	}


	@Override
	public Response deleteUser(String coId) throws Exception 
	{
		try
		{
			return coDao.deleteUser(coId);
		} 
		catch (Exception e) 
		{
			logger.info("Exception deleteUser:", e);
			return null;

		}

	}
	@Override
	public List<CoordinatorModel> getUsers() throws Exception {
		try
		{
			List<Coordinator> coo = coDao.getUsers();
			return coordinatorMapper.entityList(coo);
		} 
		catch (Exception ex)
		{
			logger.info("Exception getUsers:", ex);
		}
		return null;
	}


	@Override
	public CoordinatorModel authenticate(CoordinatorModel coordinatorModel) throws Exception {
		coordinatorModel.setpassword(CommonUtils.encriptString(coordinatorModel.getpassword()));
		Coordinator co = new Coordinator();
		BeanUtils.copyProperties(coordinatorModel, co);
		System.out.println(coordinatorModel.getemail());
		

		co = coDao.authenticate(co);
		if (co == null)
			return null;
		BeanUtils.copyProperties(co, coordinatorModel);
		return coordinatorModel;
	}


	@Override
	public String changePassword(UpdatePassword updatePassword) {
		String str = null;
		try 
		{
			Coordinator co=coDao.getUser(updatePassword.getuserId());
			if(updatePassword.getnewPassword().equals(updatePassword.getconfirmPassword()))
			{
				if( co.getpassword().equals(CommonUtils.encriptString(updatePassword.getoldPassword())))
				{			
					str = coDao.changePassword(updatePassword.getuserId(),updatePassword.getconfirmPassword(),

							CommonUtils.encriptString(updatePassword.getnewPassword()));
					return str;
				}else
					logger.error("You Entered invalid password");

				return StatusCode.ERROR.name();
			}
			else
				logger.error("New Password and Confirmpassword is Not Matching");

			return StatusCode.ERROR.name();

		} catch (Exception e) {
			logger.error("Exception in changePassword:", e.getMessage());
			return null;
		}
	}


	@SuppressWarnings("unused")
	@Override
	public String resetPassword(CoordinatorModel coordinatorModel) throws Exception {
		try {
			Coordinator co = new Coordinator();
			BeanUtils.copyProperties(coordinatorModel, co);
			co = coDao.isUserExist(co);
			System.out.println(co.getuserName());
			if (co != null) {
				String password = CommonUtils.generateRandomId();
				//String password = "prashanth";
				
				String status = coDao.resetPassword(co.getcoId(), CommonUtils.encriptString(password));
				if (status.equals(StatusCode.SUCCESS.name())) {
					String email=co.getemail();
					String pass=password;
					smtpMailSender.send(email,"Snipe It tech pvt ltd reset password","reset password ="+pass);
				}
				return status;
			} else
				return StatusCode.ERROR.name(); 
		} catch (Exception e) {
			logger.error("Exception in weeresetPassword:", e);
			return StatusCode.ERROR.name();
		}
	}


	@Override
	public boolean isUserNameExist(String userName) throws Exception {
		try {
			return coDao.isUserNameExist(userName);
		} catch (Exception e) {
			logger.info("Exception isUserNameExist:", e);
			return false;
		}
	}

	@Override
	public Response updateUserStatus(CoordinatorModel coordinatorModel) throws Exception {
		try {
			Coordinator co = new Coordinator();
			BeanUtils.copyProperties(coordinatorModel, co);
			Response response = coDao.updateUserStatus(co);
			return response;
		} catch (Exception ex) {
			logger.info("Exception in updateUserStatus:" + ex.getMessage());
		}
		return null;
	}
}
	
