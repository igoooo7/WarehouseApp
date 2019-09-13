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
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.entities.Areas;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
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
}
