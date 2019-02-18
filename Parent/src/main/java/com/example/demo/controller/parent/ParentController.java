package com.example.demo.controller.parent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.parent.ParentModel;
import com.example.demo.constants.StatusCode;
import com.example.demo.dao.parent.ParentDAO;
import com.example.demo.model.parent.ParentModel;
import com.example.demo.response.ErrorObject;
import com.example.demo.response.Response;
import com.example.demo.service.parent.ParentService;
import com.example.demo.utils.CommonUtils;






@RestController
@RequestMapping("/144")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")

public class ParentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParentController.class);

	@Autowired 
	ParentService parentService;
	
	@Autowired 
	ParentDAO parentDAO;
	
	
	/*--------------------------------------------Add Parent-----------------------------------------*/
	
	
	@RequestMapping(value = "/Parent", method = RequestMethod.POST, produces = "application/json")
	public Response addParent(@RequestBody ParentModel parent, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		logger.info("addParent: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addUser: Received request: "+ CommonUtils.getJson(parent));
		
		return parentService.addParent(parent);
		
	}
	
	
	/*--------------------------------------------get Parent by ID-----------------------------------------*/
	
	
	@RequestMapping(value = "/getParent/{parentId}", method = RequestMethod.GET, produces = "application/json")
public @ResponseBody String getParent(@PathVariable("parentId") String parentId, HttpServletRequest request,
		HttpServletResponse response) throws Exception 
{

	logger.info("getParent: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	
	ParentModel parentModel = parentService.getParent( parentId);
	
	 Response res = CommonUtils.getResponseObject("Parent Details");
	if (parentModel == null)
	{
		ErrorObject err = CommonUtils.getErrorResponse("Parents Not Found", "Parents Not Found");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	} 
	else
	{
		res.setData(parentModel);
	}
	logger.info("getParent: Sent response");
	return CommonUtils.getJson(res);
}
	

	/*--------------------------------------------get Parent by Name-----------------------------------------*/
	

	@RequestMapping(value = "/ParentExist/{parentName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String isparentNameExist(@PathVariable("parentName") String parentName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getParent: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		boolean isParentNameExist = parentService.isfullnameExist(parentName);
		Response res = CommonUtils.getResponseObject("Parent Exist");
		Map<String, Boolean> obj = new HashMap<String, Boolean>();
		obj.put("isParentNameExist", isParentNameExist);
		res.setData(obj);
		if (!isParentNameExist) {
			res.setStatus(StatusCode.ERROR.name());
		}
		logger.info("getUser: Sent response");
		return CommonUtils.getJson(res);
	}

	

/*--------------------------------------------update Parent -----------------------------------------*/

@RequestMapping(value = "/updateParent", method = RequestMethod.PUT, produces = "application/json")
public Response updateParent(@RequestBody ParentModel parentModel, HttpServletRequest request, HttpServletResponse response)
		throws Exception 
{
	logger.info("updateParent: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("updateParent: Received request: " + CommonUtils.getJson(parentModel));
	
	return parentService.updateParent(parentModel);
	
}
/*--------------------------------------------get Parents -----------------------------------------*/
@RequestMapping(value = "/Parents", method = RequestMethod.GET, produces = "application/json")
public @ResponseBody String getParents(HttpServletRequest request, HttpServletResponse response) throws Exception 
{
	logger.info("getParents: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	List<ParentModel> model = parentService.getParents();
	
	
	Response res = CommonUtils.getResponseObject("List of Parents");
	if (model == null) {
		ErrorObject err = CommonUtils.getErrorResponse("Parents Not Found", "Parents Not Found");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	} else {
		res.setData(model);
	}
	logger.info("getParents: Sent response");
	return CommonUtils.getJson(res);
}

/*--------------------------------------------delete Parents -----------------------------------------*/
@RequestMapping(value="/deleteParent/{ParentId}",method = RequestMethod.DELETE, produces = "application/json")
public @ResponseBody Response deleteParent (@PathVariable("ParentId") String parentId, HttpServletRequest request, HttpServletResponse response) throws Exception
{
	logger.info("getParent: Received request:" +request.getRequestURL().toString()
			+((request.getQueryString()==null)?  "" : "?" +request.getQueryString().toString()));
	
	return parentService.deleteParent(parentId);
}
/*--------------------------------------------Parent login -----------------------------------------*/
@RequestMapping(value="/login",method = RequestMethod.POST, produces ="application/json")
public @ResponseBody String authenticate (@RequestBody ParentModel parent, HttpServletRequest request, HttpServletResponse response) throws Exception

{
	logger.info("authenticate: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("authenticate :Received request: " + CommonUtils.getJson(parent));
	
	parent =parentService.authenticate(parent);
	
	Response res=CommonUtils.getResponseObject("authenticate Parent");
	if(parent==null)
	{
		ErrorObject err=CommonUtils.getErrorResponse("Invalid Fullname or Password", "Invalid Fullname or Password");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	}
	{
		res.setData(parent);
		
	}
	logger.info("authenticate:sent response");
	return CommonUtils.getJson(res);
}
/*--------------------------------------------Forgot Password -----------------------------------------*/
@RequestMapping(value="/forgotPassword",method = RequestMethod.PUT, produces ="application/json")
public @ResponseBody String resetPassword(@RequestBody ParentModel parentModel, HttpServletRequest request,
		HttpServletResponse response) throws Exception
{
	logger.info("forgotPassword: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?1" + request.getQueryString().toString()));
	logger.info("forgotPassword: Received request: "+CommonUtils.getJson(parentModel));
	
	String status = parentService.forgotPassword(parentModel);
	Response res = CommonUtils.getResponseObject("forgot password");
	if (status.equalsIgnoreCase(StatusCode.ERROR.name())) {
		ErrorObject err = CommonUtils.getErrorResponse("forgot password failed", "forgot password failed");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
}
	logger.info("forgotPassword: Sent response");
	return CommonUtils.getJson(res);
}
/*--------------------------------------------Parent status -----------------------------------------*/

@RequestMapping(value = "/ParentStatus", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
public Response updateParentStatus(@RequestBody ParentModel parentModel, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.info("updateParentStatus: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("updateParentStatus: Received request: " + CommonUtils.getJson(parentModel));
	return parentService.updateParentStatus(parentModel);


}
}















