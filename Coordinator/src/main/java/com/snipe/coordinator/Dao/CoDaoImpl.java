package com.snipe.coordinator.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.snipe.coordinator.Constants.*;
import com.snipe.coordinator.domain.Coordinator;
import com.snipe.coordinator.Response.Response;
import com.snipe.coordinator.Utils.*;
import com.snipe.coordinator.Dao.CoDao;




@Repository
@Transactional
public class CoDaoImpl implements CoDao
{
	private static final Logger logger = LoggerFactory.getLogger(CoDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Response addUser(Coordinator co) throws Exception {
		Response response = CommonUtils.getResponseObject("Add user data");
		try 
		{
			entityManager.persist(co);
			response.setStatus(StatusCode.SUCCESS.name());
		} 
		catch (Exception e) 
		{
			logger.error("Exception in addUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
		 
	}

	@Override
	public Response deleteUser(String coId) throws Exception {
		Response response = CommonUtils.getResponseObject("Delete user data");
		try {
			

			Coordinator co=getUser(coId);
			co.setActive(false);


		
			entityManager.flush();

			response.setStatus(StatusCode.SUCCESS.name());
			
		} 
		catch(Exception ex)
		{
			logger.error("Exception in deleteUser", ex);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(ex.getMessage());
		}
		return response;	
	}

	@Override
	public Coordinator getUser(String coId) throws Exception {

		try 
		{
			String hql = "From Coordinator where coId=?0 and isActive=true";
			return (Coordinator) entityManager.createQuery(hql).setParameter(0, coId).getSingleResult();
		} 
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		} 
		catch (Exception e) 
		{
			logger.error("Exception in getUser"+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordinator> getUsers() throws Exception {
		try 
		{
			String hql = "FROM Coordinator  where isActive=true";
			
			return (List<Coordinator>) entityManager.createQuery(hql).getResultList();
		} 
		catch (Exception e)
		{
			logger.error("Exception in getUsers", e);
		}
		return null;
	}

	@Override
	public Coordinator authenticate(Coordinator co) throws Exception {
		try 
		{
		String hql = "FROM Coordinator where userName=:userName  and password=:password  and isActive=true";
		return (Coordinator) entityManager.createQuery(hql).setParameter("userName", co.getuserName()).setParameter("password", co.getpassword()).getSingleResult();
		} 
		catch (Exception e)
		{
		logger.error("Exception in auteneticate", e);
	}
		return null;
	}

	@Override
	public Response updateUser(Coordinator co) throws Exception {
		Response response = CommonUtils.getResponseObject("Update user data");
		try 
		{	
			Coordinator cos = getUser(co.getcoId());
			cos.setuserName(co.getuserName());
			cos.setfirstName(co.getfirstName());
			cos.setlastName(co.getlastName());
			cos.setemail(co.getemail());
			cos.setActive(true);
			
			
			cos.setpassword(CommonUtils.encriptString(co.getpassword()));
			
		
			
			
			
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} 
		catch (Exception e) 
		{
			logger.error("Exception in updateUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  response;
	}

	

	@Override
	public String changePassword(String coId, String confirmpassword, String encriptString) throws Exception {
		try 
		{
			Coordinator co = getUser(coId);
			co.setpassword(encriptString);
			entityManager.flush();
			return StatusCode.SUCCESS.name();
		} 
		catch (Exception e) 
		{
			logger.error("Exception in changePassword", e);
			return StatusCode.ERROR.name();	
		}
	}
	
	@Override
	public boolean isUserNameExist(String userName) throws Exception {
		try {
			String hql = "FROM Coordinator WHERE userName=? and  isActive=true ";
			int count = entityManager.createQuery(hql).setParameter(1, userName).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isUserNameExist: ", e);
		}
		return false;
		/*try {
			String hql = "FROM User WHERE userName=? and isActive=true";
			int count = entityManager.createQuery(hql).setParameter(0, userName).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isUserNameExist: ", e);
		}
		return false;*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coordinator> getUsersByRole(String role) throws Exception {
		try { 
			
			String hql = "FROM Coordinator  WHERE role=?and isActive=true " ;
			return (List<Coordinator>) entityManager.createQuery(hql).setParameter(0, role).getResultList();
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception in getUsersByRole", e);
			return null;
		}
	} 

	@Override
	public Coordinator isUserExist(Coordinator co) throws Exception {
		try {
			String hql = "FROM Coordinator where email=?1 and isActive=true";
			return (Coordinator ) entityManager.createQuery(hql).setParameter(1, co.getemail()).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in isUserExist", e);
		}
		return co;
	}

	@Override
	public String resetPassword(String coId, String encriptString) throws Exception {
		try {
			Coordinator coo = getUser(coId);
			coo.setpassword(encriptString);
			entityManager.flush();
			return StatusCode.SUCCESS.name();
	} catch (Exception e) {
		logger.error("Exception in 333 sresetPassword", e);
		return StatusCode.ERROR.name();
	}
	}

	@Override
	public Response updateUserStatus(Coordinator co) throws Exception {
		Response response = CommonUtils.getResponseObject("Update User data");
		try {
			Coordinator co1 = getUser(co.getcoId());
			
			
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in deleteUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}

	@Override
	
	public List<Coordinator> userAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

