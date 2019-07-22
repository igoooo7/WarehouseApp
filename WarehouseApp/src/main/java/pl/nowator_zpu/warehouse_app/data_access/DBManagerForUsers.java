package pl.nowator_zpu.warehouse_app.data_access;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import javassist.bytecode.stackmap.TypeData.ClassName;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class DBManagerForUsers {
	
	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName());

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private static void createManager() {
		emf = Persistence.createEntityManagerFactory("db_warehouse");
		em = emf.createEntityManager();
	}

	private static void destroyManager() {
		em.close();
		emf.close();
	}

	@SuppressWarnings("finally")
	public User getUserByUserName(String userName) {		 
		
		User userResult = new User(0,"","","","","","",false);
		
		try	{
			
		createManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Users> user = criteriaQuery.from(Users.class);	
		Root<UserRights> userRights = criteriaQuery.from(UserRights.class);	
		Root<JobTitles> jobTitle = criteriaQuery.from(JobTitles.class);	
		
		Path<String> name = user.get("userName");
		criteriaQuery.multiselect(user,userRights,jobTitle);										
		
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.and(criteriaBuilder.equal(user.get("userRights"), userRights.get("userRightsId")),		
				criteriaBuilder.equal(user.get("jobTitles"), jobTitle.get("jobTitleId"))), criteriaBuilder.equal(name, userName)));
		
		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		
		Object[] queryResult = query.getSingleResult();
		
		Users u = (Users)queryResult[0];
		UserRights ur = (UserRights)queryResult[1];
		JobTitles jt = (JobTitles)queryResult[2];
		
		userResult.setUserId(u.getUserId());
		userResult.setJobTitle(jt.getJobTitle());
		userResult.setUserRights(ur.getUserRights());
		userResult.setUserName(u.getUserName());
		userResult.setUserPassword(u.getUserPassword());
		userResult.setFirstName(u.getFirstName());
		userResult.setLastName(u.getLastName());		
		
		destroyManager();
		
		return userResult;
		}
		catch(Exception e){ 
		 LOGGER.log(Level.WARNING, e.toString());
		 userResult = null;			
		}
		finally {
			return userResult;
		}		
	}
	
}
