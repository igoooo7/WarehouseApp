package pl.nowatorzpu.warehouseapp.dataaccess;

import java.util.ArrayList;
import java.util.List;
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
import pl.nowatorzpu.warehouseapp.applicationclasses.User;
import pl.nowatorzpu.warehouseapp.entities.JobTitles;
import pl.nowatorzpu.warehouseapp.entities.UserRights;
import pl.nowatorzpu.warehouseapp.entities.Users;

public class DBManagerForUsers {

	private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

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

	// User
	public User getUserByUserName(String name) {

		User result = new User();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Users> userRoot = criteriaQuery.from(Users.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);
			Root<JobTitles> jobTitleRoot = criteriaQuery.from(JobTitles.class);

			Path<String> un = userRoot.get("userName");
			criteriaQuery.multiselect(userRoot, userRightsRoot, jobTitleRoot);

			criteriaQuery
					.where(criteriaBuilder.and(
							criteriaBuilder.and(
									criteriaBuilder.equal(userRoot.get("userRights"),
											userRightsRoot.get("userRightsId")),
									criteriaBuilder.equal(userRoot.get("jobTitles"), jobTitleRoot.get("jobTitleId"))),
							criteriaBuilder.equal(un, name)));

			TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

			Object[] queryResult = query.getSingleResult();

			Users u = (Users) queryResult[0];
			UserRights ur = (UserRights) queryResult[1];
			JobTitles jt = (JobTitles) queryResult[2];

			result.setUserId(u.getUserId());
			result.setJobTitle(jt.getJobTitle());
			result.setUserRights(ur.getUserRights());
			result.setUserRightsLevel(ur.getUserRightsId());
			result.setUserName(u.getUserName());
			result.setUserPassword(u.getUserPassword());
			result.setFirstName(u.getFirstName());
			result.setLastName(u.getLastName());

			destroyManager();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public ArrayList<User> getAllUsers() {

		ArrayList<User> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Users> userRoot = criteriaQuery.from(Users.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);
			Root<JobTitles> jobTitleRoot = criteriaQuery.from(JobTitles.class);

			criteriaQuery.multiselect(userRoot, userRightsRoot, jobTitleRoot);

			criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.and(
					criteriaBuilder.equal(userRoot.get("userRights"), userRightsRoot.get("userRightsId")),
					criteriaBuilder.equal(userRoot.get("jobTitles"), jobTitleRoot.get("jobTitleId")))));

			TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

			List<Object[]> queryResult = query.getResultList();

			for (Object[] objects : queryResult) {

				Users u = (Users) objects[0];
				UserRights ur = (UserRights) objects[1];
				JobTitles jt = (JobTitles) objects[2];

				User user = new User();

				user.setUserId(u.getUserId());
				user.setJobTitle(jt.getJobTitle());
				user.setUserRights(ur.getUserRights());
				user.setUserRightsLevel(ur.getUserRightsId());
				user.setUserName(u.getUserName());
				user.setUserPassword(u.getUserPassword());
				user.setFirstName(u.getFirstName());
				user.setLastName(u.getLastName());

				result.add(user);
			}

			destroyManager();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Users getUserEntityByUserName(String userName) {

		Users result = new Users();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
			Root<Users> userRoot = criteriaQuery.from(Users.class);

			Path<String> u = userRoot.get("userName");

			criteriaQuery.select(userRoot);

			criteriaQuery.where(criteriaBuilder.equal(u, userName));

			TypedQuery<Users> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}
	
	public Boolean newUser(Users user) {

		try {

			createManager();

			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	public Boolean deleteUser(User user) {

		try {

			createManager();

			Users u = em.find(Users.class, user.getUserId());

			if (u != null) {
				em.getTransaction().begin();
				em.remove(u);
				em.getTransaction().commit();
			}

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	// Job title
	public ArrayList<String> getAllJobTitles() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<JobTitles> criteriaQuery = criteriaBuilder.createQuery(JobTitles.class);
			Root<JobTitles> jobTitleRoot = criteriaQuery.from(JobTitles.class);

			criteriaQuery.select(jobTitleRoot);
			TypedQuery<JobTitles> query = em.createQuery(criteriaQuery);

			List<JobTitles> queryResult = query.getResultList();

			for (JobTitles jt : queryResult) {
				result.add(jt.getJobTitle());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public JobTitles getJobTitleByTitle(String title) {

		JobTitles result = new JobTitles();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<JobTitles> criteriaQuery = criteriaBuilder.createQuery(JobTitles.class);
			Root<JobTitles> jobTitlesRoot = criteriaQuery.from(JobTitles.class);

			Path<String> jt = jobTitlesRoot.get("jobTitle");

			criteriaQuery.select(jobTitlesRoot);

			criteriaQuery.where(criteriaBuilder.equal(jt, title));

			TypedQuery<JobTitles> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// User rights
	public ArrayList<String> getAllUserRights() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<UserRights> criteriaQuery = criteriaBuilder.createQuery(UserRights.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);

			criteriaQuery.select(userRightsRoot);
			TypedQuery<UserRights> query = em.createQuery(criteriaQuery);

			List<UserRights> queryResult = query.getResultList();

			for (UserRights ur : queryResult) {
				result.add(ur.getUserRights());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public UserRights getUserRightsByRights(String rights) {

		UserRights result = new UserRights();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<UserRights> criteriaQuery = criteriaBuilder.createQuery(UserRights.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);

			Path<String> ur = userRightsRoot.get("userRights");

			criteriaQuery.select(userRightsRoot);

			criteriaQuery.where(criteriaBuilder.equal(ur, rights));

			TypedQuery<UserRights> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

}
