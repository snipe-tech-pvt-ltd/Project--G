package com.example.demo.dao.parent;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.constants.StatusCode;
import com.example.demo.domain.parent.Parent;
//import com.example.demo.model.parent.UpdatePassword;
//import com.example.demo.model.parent.ParentModel;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;




@Repository
@Transactional
public  class ParentDAOImpl implements ParentDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ParentDAOImpl.class);


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Response addParent(Parent parent) throws Exception {
		Response response = CommonUtils.getResponseObject("Add Parent data");
		try 
		{
			entityManager.persist(parent);
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
	public Response deleteParent(String parentId) throws Exception {
		Response response = CommonUtils.getResponseObject("Delete Parent data");
		try {
			

			Parent parent=getParent(parentId);
			parent.setActive(false);


		
			entityManager.flush();

			response.setStatus(StatusCode.SUCCESS.name());
			
		} 
		catch(Exception ex)
		{
			logger.error("Exception in deleteParent", ex);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(ex.getMessage());
		}
		return response;	
	}

	
	
@Override
	public boolean isParentNameExist(String parentName) throws Exception {
		try {
			String hql = "FROM User WHERE fullname=?1 and isActive=true";
			int count = entityManager.createQuery(hql).setParameter(0, parentName).getResultList().size();
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isparentNameExist: ", e);
		}
		return false;
	}
	
	
	
	@Override
	public Parent getParent(String parentfullname) throws Exception {

		try 
		{
			String hql = "From Parent where parentId=?1 and isActive=true";
			return (Parent) entityManager.createQuery(hql).setParameter(1, parentfullname).getSingleResult();
		} 
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		} 
		catch (Exception e) 
		{
			logger.error("Exception in getParent"+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parent> getParents() throws Exception {
		try 
		{
			String hql = "FROM Parent where isActive=true";
			
			return (List<Parent>) entityManager.createQuery(hql).getResultList();
		} 
		catch (Exception e)
		{
			logger.error("Exception in getParents", e);
		}
		return null;
	}

	@Override
	public Parent authenticate(Parent parent) throws Exception {
		try 
		{
		String hql = "FROM Parent where email=:email  and password=:password  and isActive=true";
		return (Parent) entityManager.createQuery(hql).setParameter("email", parent.getemail()).setParameter("password", parent.getpassword()).getSingleResult();
		} 
		catch (Exception e)
		{
		logger.error("Exception in auteneticate", e);
	}
		return null;
	}

	@Override
	public Response updateParent(Parent parent) throws Exception {
		Response response = CommonUtils.getResponseObject("Update Parent data");
		try 
		{	
			Parent parents = getParent(parent.getparentId());
			parents.setfullname(parent.getfullname());
	        parents.setphoneno(parent.getphoneno());
			parents.setemail(parent.getemail());
			parents.setActive(true);
			parents.setpassword(CommonUtils.encriptString(parent.getpassword()));
	        entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} 
		catch 	(Exception e) 
		{
			logger.error("Exception in updateParent", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  response;
	}

	@Override
	public String forgotPassword(String parentId, String encriptString) throws Exception {
		try {
			Parent fran = getParent(parentId);
			fran.setpassword(encriptString);
			entityManager.flush();
			return StatusCode.SUCCESS.name();
	} catch (Exception e) {
		logger.error("Exception in forgotPassword", e);
		return StatusCode.ERROR.name();
	}
	}

	@Override
	public boolean isfullnameExist(String fullname) throws Exception {
		try {
			String hql = "FROM Parent WHERE fullname=?1 and  isActive=true ";
			int count = entityManager.createQuery(hql).setParameter(1, fullname).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isfullnameExist: ", e);
		}
		return false;
	}
	@Override
	public Response updateParentStatus(Parent parent) throws Exception {
		Response response = CommonUtils.getResponseObject("Update Parent data");
		try {
			Parent parent1 = getParent(parent.getparentId());
			
			
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in deleteParent", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}

	@Override
	public Parent isParentExist(Parent parent) throws Exception {
		try {
			String hql = "FROM Parent where email=?1 and isActive=true";
			return (Parent) entityManager.createQuery(hql).setParameter(1, parent.getemail()).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in isParentExist", e);
		}
	
	
		return parent;
	}





/**	@Override
	public String changePassword(String ParentId, String confirmpassword, String encriptString) throws Exception {
		try 
		{
			Parent Parent = getParent(ParentId);
			Parent.setpassword(encriptString);
			entityManager.flush();
			return StatusCode.SUCCESS.name();
		} 
		catch (Exception e) 
		{
			logger.error("Exception in changePassword", e);
			return StatusCode.ERROR.name();	
		}
	}**/
	
		/*try {
			String hql = "FROM User WHERE userName=? and isActive=true";
			int count = entityManager.createQuery(hql).setParameter(0, userName).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isUserNameExist: ", e);
		}
		return false;**/
/**	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parent> getParentsByRole(String role) throws Exception {
		try { 
			
			String hql = "FROM Parent  WHERE role=?and isActive=true " ;
			return (List<Parent>) entityManager.createQuery(hql).setParameter(0, role).getResultList();
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception in getParentsByRole", e);
			return null;
		}
	} **/



	




}
	




