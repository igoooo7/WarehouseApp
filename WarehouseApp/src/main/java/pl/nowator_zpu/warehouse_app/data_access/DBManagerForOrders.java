package pl.nowator_zpu.warehouse_app.data_access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import pl.nowator_zpu.warehouse_app.application_classes.Order;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.entities.Areas;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.entities.Orders;
import pl.nowator_zpu.warehouse_app.entities.PartGroups;
import pl.nowator_zpu.warehouse_app.entities.Parts;
import pl.nowator_zpu.warehouse_app.entities.Projects;
import pl.nowator_zpu.warehouse_app.entities.Racks;
import pl.nowator_zpu.warehouse_app.entities.Shelfs;
import pl.nowator_zpu.warehouse_app.entities.Units;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class DBManagerForOrders {

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

	// Project
	public ArrayList<String> getAllProjects() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Projects> criteriaQuery = criteriaBuilder.createQuery(Projects.class);
			Root<Projects> areaRoot = criteriaQuery.from(Projects.class);

			criteriaQuery.select(areaRoot);
			TypedQuery<Projects> query = em.createQuery(criteriaQuery);

			List<Projects> queryResult = query.getResultList();

			for (Projects p : queryResult) {
				result.add(p.getProjectName());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Projects getProjectEntityByProject(String project) {

		Projects result = new Projects();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Projects> criteriaQuery = criteriaBuilder.createQuery(Projects.class);
			Root<Projects> projectRoot = criteriaQuery.from(Projects.class);

			Path<String> p = projectRoot.get("projectName");

			criteriaQuery.select(projectRoot);

			criteriaQuery.where(criteriaBuilder.equal(p, project));

			TypedQuery<Projects> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Order
	public Boolean newOrder(Orders order) {

		try {

			createManager();

			em.getTransaction().begin();
			em.persist(order);
			em.getTransaction().commit();

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	public ArrayList<Order> getAllOrders() {

		ArrayList<Order> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Orders> orderRoot = criteriaQuery.from(Orders.class);

			Root<Parts> partRoot = criteriaQuery.from(Parts.class);
			partRoot.join("manufacturers");

			Root<Manufacturers> manufacturerRoot = criteriaQuery.from(Manufacturers.class);

			Root<Projects> projectRoot = criteriaQuery.from(Projects.class);

			Root<Users> userRoot = criteriaQuery.from(Users.class);
			userRoot.join("jobTitles");
			userRoot.join("userRights");

			criteriaQuery.multiselect(orderRoot, partRoot, manufacturerRoot, projectRoot, userRoot);

			criteriaQuery.where(criteriaBuilder
					.and(criteriaBuilder.and(criteriaBuilder.equal(orderRoot.get("parts"), partRoot.get("partId")),
							criteriaBuilder.equal(partRoot.get("manufacturers"),
									manufacturerRoot.get("manufacturerId")),
							criteriaBuilder.equal(orderRoot.get("projects"), projectRoot.get("projectId")),
							criteriaBuilder.equal(orderRoot.get("users"), userRoot.get("userId")))));

			TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

			List<Object[]> queryResult = query.getResultList();

			for (Object[] objects : queryResult) {

				Orders o = (Orders) objects[0];
				Parts pa = (Parts) objects[1];
				Manufacturers m = (Manufacturers) objects[2];
				Projects pr = (Projects) objects[3];
				Users us = (Users) objects[4];

				Order order = new Order();

				order.setDescription(pa.getDescription());
				order.setManufacturer(m.getManufacturer());
				order.setOrderCode(pa.getOrderCode());
				order.setOrderDate(o.getOrderDate());
				order.setOrderId(o.getOrderId());
				order.setPartCount(o.getPartCount());
				order.setProject(pr.getProjectName());
				order.setUser(us.getFirstName() + " " + us.getLastName());

				result.add(order);
			}

			destroyManager();

			Collections.sort(result, new Comparator<Order>() {
				public int compare(Order o1, Order o2) {
					if (o1.getOrderDate() == null || o2.getOrderDate() == null)
						return 0;
					return o1.getOrderDate().compareTo(o2.getOrderDate());
				}
			});

			Collections.reverse(result);

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}
}
