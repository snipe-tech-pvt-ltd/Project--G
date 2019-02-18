package com.snipe.coordinator.Utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snipe.coordinator.Constants.Constants;
import com.snipe.coordinator.Constants.StatusCode;
import com.snipe.coordinator.Response.ErrorObject;
import com.snipe.coordinator.Response.Response;







public class CommonUtils 
{
	static ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	private static HttpSession session = null;

	public static String encriptString(String strToEncript) {

		String returnString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strToEncript.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			returnString = sb.toString();
			return returnString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return returnString;
		}
	}

	public static String generateRandomId() {
		return UUID.randomUUID().toString();
	}

	public static String getJson(Object obj) {
		try {
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}

	public static Object getObject(String str) throws IOException {
		try {
			return mapper.readValue(str, Object.class);
		} catch (JsonProcessingException e) {
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}

	public static Response getResponseObject(String message) {
		Response response = new Response();
		response.setStatus(StatusCode.SUCCESS.name());
		response.setMessage(message);
		return response;
	}
	
	public static Response getResponseObject1(String message)
	{
		Response response=new Response();
		response.setStatus(StatusCode.ERROR.name());
		response.setMessage(message);
		return response;
	}

	public static ErrorObject getErrorResponse(String title, String detail) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setTitle(title);
		errorObject.setDetail(detail);
		return errorObject;
	}

	public static HttpSession geSsession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (session == null)
			session = attr.getRequest().getSession(true);
		return session;
	}

	public static String getUserId(String userName) {
		HttpSession session = CommonUtils.geSsession();
		return (String) session.getAttribute(userName);
	}

	
}


