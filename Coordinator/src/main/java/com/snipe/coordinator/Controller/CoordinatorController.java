package com.snipe.coordinator.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.coordinator.Constants.StatusCode;
import com.snipe.coordinator.CoordinatorService.CoordinatorService;
import com.snipe.coordinator.Dao.CoDao;
import com.snipe.coordinator.Model.CoordinatorModel;
import com.snipe.coordinator.Model.UpdatePassword;
import com.snipe.coordinator.Response.ErrorObject;
import com.snipe.coordinator.Response.Response;
import com.snipe.coordinator.Utils.CommonUtils;


@Transactional(dontRollbackOn = Exception.class)

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class CoordinatorController  
{
	private static final Logger logger = LoggerFactory.getLogger(CoordinatorController.class);
	
	@Autowired
	CoordinatorService coordinatorService;
	
	@Autowired
	CoDao coDao;

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public Response addUser(@RequestBody CoordinatorModel com, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		logger.info("addUser: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addUser: Received request: "+ CommonUtils.getJson(com));
		
		return coordinatorService.addUser(com);
		
	}
	
	/*---------------------------------------/*  Update User /*-----------------------------------------*/
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT, produces = "application/json")
	public Response updateUser(@RequestBody CoordinatorModel coordinatorModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		logger.info("updateUser: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateUser: Received request: " + CommonUtils.getJson(coordinatorModel));
		
		return coordinatorService.updateUser(coordinatorModel);
		
	}
	
	
	
	

	/*---------------------------------------/*  Get User By Id /*-----------------------------------------*/
	
	
	@RequestMapping(value = "/getuser/{coId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUser(@PathVariable("coId") String coId, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{

		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		CoordinatorModel coordinatorModel = coordinatorService.getUser(coId);
		Response res = CommonUtils.getResponseObject("User Details");
		if (coordinatorModel == null)
		{
			ErrorObject err = CommonUtils.getErrorResponse("Users Not Found", "Users Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} 
		else
		{
			res.setData(coordinatorModel);
		}
		logger.info("getUser: Sent response");
		return CommonUtils.getJson(res);
	}
	
	@RequestMapping(value = "/userAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		logger.info("getUsers: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<CoordinatorModel> com = coordinatorService.getUsers();
		
		
		Response res = CommonUtils.getResponseObject("List of Users");
		if (com == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Users Not Found", "Users Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(com);
		}
		logger.info("getUsers: Sent response");
		return CommonUtils.getJson(res);
	}
	/*---------------------------------------/*  Delete User By Id /*-----------------------------------------*/

	@RequestMapping(value = "/deleteuser/{coId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Response deleteUser(@PathVariable("coId") String coId, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		
		return coordinatorService.deleteUser(coId);
	}

/*---------------------------------------/*  User Login /*-----------------------------------------*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String authenticate(@RequestBody CoordinatorModel com, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		System.out.println("prashanth works");
		logger.info("authenticate: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("authenticate :Received request: " + CommonUtils.getJson(com));
		
		com = coordinatorService.authenticate(com);
		
		Response res = CommonUtils.getResponseObject("authenticate user");
		if (com == null) 
		{
			ErrorObject err = CommonUtils.getErrorResponse("Invalid UserName or Password",
					"Invalid UserName or Password");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} 
		 
		{
			res.setData(com);
		}
		logger.info("authenticate: Sent response");
		return CommonUtils.getJson(res);
	}
	
	
	/*---------------------------------------/*  User Change Password /*-----------------------------------------*/
	
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String changePassword(@RequestBody UpdatePassword updatePassword, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		logger.info("changePassword: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("changePassword :Received request: " + CommonUtils.getJson(updatePassword));
		String status = coordinatorService.changePassword(updatePassword);
		Response res = CommonUtils.getResponseObject("Update Password");
		res.setStatus(status);
		if (status.equalsIgnoreCase(StatusCode.ERROR.name())) 
		{
			ErrorObject err = CommonUtils.getErrorResponse("Update Password Failed", "Update Password Failed");
			res.setErrors(err);
		}
		logger.info("changePassword: Sent response");
		return CommonUtils.getJson(res);
	}
	/*---------------------------------------/*  User Reset Password /*-----------------------------------------*/
	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String resetPassword(@RequestBody CoordinatorModel coordinatorModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("resetPassword: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("resetPassword :Received request: " + CommonUtils.getJson(coordinatorModel));
		String status = coordinatorService.resetPassword(coordinatorModel);
		Response res = CommonUtils.getResponseObject("Reset password");
		if (status.equalsIgnoreCase(StatusCode.ERROR.name())) {
			ErrorObject err = CommonUtils.getErrorResponse("Reset 222password failed", "Reset password failed");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		}
		logger.info("resetPassword: Sent response");
		return CommonUtils.getJson(res);
	} 
	
	/*---------------------------------------/* User Status /*-----------------------------------------*/
	
	@RequestMapping(value = "/userStatus", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public Response updateUserStatus(@RequestBody CoordinatorModel coordinatorModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("updateUserStatus: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateUserStatus: Received  request: " + CommonUtils.getJson(coordinatorModel));
		return coordinatorService.updateUserStatus(coordinatorModel);
	}


}