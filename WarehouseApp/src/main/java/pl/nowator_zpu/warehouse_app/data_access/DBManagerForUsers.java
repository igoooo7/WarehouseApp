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

import org.eclipse.jface.text.templates.GlobalTemplateVariables.User;

import javassist.bytecode.stackmap.TypeData.ClassName;
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
	public Users getUserByUserName(String userName) {		 
		
		Users userResult = null;
		
		try	{
			
		createManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		Root<Users> user = criteriaQuery.from(Users.class);		
		
		Path<String> usersNames = user.get("userName");
		criteriaQuery.select(user).where(criteriaBuilder.equal(usersNames, userName));
		
		TypedQuery<Users> query = em.createQuery(criteriaQuery);
		userResult = query.getSingleResult();
				
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
