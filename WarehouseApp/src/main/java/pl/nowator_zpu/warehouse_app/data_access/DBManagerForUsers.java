package pl.nowator_zpu.warehouse_app.data_access;

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
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

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

		User userResult = new User();

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

			userResult.setUserId(u.getUserId());
			userResult.setJobTitle(jt.getJobTitle());
			userResult.setUserRights(ur.getUserRights());
			userResult.setUserRightsLevel(ur.getUserRightsId());
			userResult.setUserName(u.getUserName());
			userResult.setUserPassword(u.getUserPassword());
			userResult.setFirstName(u.getFirstName());
			userResult.setLastName(u.getLastName());

			destroyManager();

			return userResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public ArrayList<User> getAllUsers() {

		ArrayList<User> usersResult = new ArrayList<>();

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

				usersResult.add(user);
			}

			destroyManager();

			return usersResult;
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

		ArrayList<String> jobTitlesResult = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<JobTitles> criteriaQuery = criteriaBuilder.createQuery(JobTitles.class);
			Root<JobTitles> jobTitleRoot = criteriaQuery.from(JobTitles.class);

			criteriaQuery.select(jobTitleRoot);
			TypedQuery<JobTitles> query = em.createQuery(criteriaQuery);

			List<JobTitles> queryResult = query.getResultList();

			for (JobTitles jt : queryResult) {
				jobTitlesResult.add(jt.getJobTitle());
			}

			return jobTitlesResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public JobTitles getJobTitleByTitle(String title) {

		JobTitles jobTitleResult = new JobTitles();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<JobTitles> criteriaQuery = criteriaBuilder.createQuery(JobTitles.class);
			Root<JobTitles> jobTitlesRoot = criteriaQuery.from(JobTitles.class);

			Path<String> jt = jobTitlesRoot.get("jobTitle");

			criteriaQuery.select(jobTitlesRoot);

			criteriaQuery.where(criteriaBuilder.equal(jt, title));

			TypedQuery<JobTitles> query = em.createQuery(criteriaQuery);

			jobTitleResult = query.getSingleResult();

			return jobTitleResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// User rights
	public ArrayList<String> getAllUserRights() {

		ArrayList<String> userRightsResult = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<UserRights> criteriaQuery = criteriaBuilder.createQuery(UserRights.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);

			criteriaQuery.select(userRightsRoot);
			TypedQuery<UserRights> query = em.createQuery(criteriaQuery);

			List<UserRights> queryResult = query.getResultList();

			for (UserRights ur : queryResult) {
				userRightsResult.add(ur.getUserRights());
			}

			return userRightsResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public UserRights getUserRightsByRights(String rights) {

		UserRights userRightsResult = new UserRights();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<UserRights> criteriaQuery = criteriaBuilder.createQuery(UserRights.class);
			Root<UserRights> userRightsRoot = criteriaQuery.from(UserRights.class);

			Path<String> ur = userRightsRoot.get("userRights");

			criteriaQuery.select(userRightsRoot);

			criteriaQuery.where(criteriaBuilder.equal(ur, rights));

			TypedQuery<UserRights> query = em.createQuery(criteriaQuery);

			userRightsResult = query.getSingleResult();

			return userRightsResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

}
