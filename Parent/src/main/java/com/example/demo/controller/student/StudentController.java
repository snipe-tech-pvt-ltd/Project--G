package com.example.demo.controller.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.StatusCode;
import com.example.demo.dao.student.StudentDAO;
import com.example.demo.model.student.StudentModel;
import com.example.demo.response.ErrorObject;
import com.example.demo.response.Response;
import com.example.demo.service.student.StudentService;
import com.example.demo.utils.CommonUtils;



@RestController
@RequestMapping("/144")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class StudentController {
private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;	
	
	@Autowired
	StudentDAO studentDAO;

	//----------------------------Add Admission----------------------------------	
	@RequestMapping(value="/student",method=RequestMethod.POST,produces="application/json" )
	public Response  addStudent(@Valid @RequestBody StudentModel model,HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) 
			throws Exception {
		return  (Response) studentService.addStudent(model);
	}
	
	@RequestMapping(value = "/admissions", method = RequestMethod.PUT, produces = "application/json")
	public Response updateStudent(@RequestBody StudentModel studentModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("studentStudent: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateAdmission: Received request: " + CommonUtils.getJson(studentModel));
		return studentService.updateStudent(studentModel);
	}

	
	
	@RequestMapping(value = "/student/{stdId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAdmission(@PathVariable("stdId") String stdId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		StudentModel studentModel = studentService.getStudent(stdId);
		Response res = CommonUtils.getResponseObject("Admission Details");
		if (studentModel == null) {
			ErrorObject err = CommonUtils.getErrorResponse("admission Not Found", "admission Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(studentModel);
		}
		logger.info("getUser: Sent response");
		return CommonUtils.getJson(res);
	}

	@RequestMapping(value = "/studentId/{stdId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Response deleteAdmission(@PathVariable("stdId") String stdId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getStudent: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		return studentService.deleteStudent(stdId);
	}
	
	//----------------------------Get All Admission----------------------------------
		@RequestMapping(value="/students/all", method=RequestMethod.GET, produces="application/json")
		public @ResponseBody String allAdmission(HttpServletRequest request, HttpServletResponse response)throws Exception{
			logger.info("allstudents: Received request: " + request.getRequestURL().toString()
					+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
			List<StudentModel>model=studentService.allStudent();
			Response res = CommonUtils.getResponseObject("ALL Admission Details");
		
			if (model.isEmpty()) {
				ErrorObject err = CommonUtils.getErrorResponse("Admission Not Found", "Admission Not Found");
				res.setErrors(err);
				res.setStatus(StatusCode.ERROR.name());
				res.setMessage("There are no Admission.");
			} else {
				res.setData(model);
			}
			logger.info("Category: Sent response");
			return CommonUtils.getJson(res);
		}


}
