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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import javassist.bytecode.stackmap.TypeData.ClassName;
import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.entities.Areas;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.Manufacturers;
import pl.nowator_zpu.warehouse_app.entities.PartGroups;
import pl.nowator_zpu.warehouse_app.entities.Parts;
import pl.nowator_zpu.warehouse_app.entities.Racks;
import pl.nowator_zpu.warehouse_app.entities.Shelfs;
import pl.nowator_zpu.warehouse_app.entities.Units;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class DBManagerForParts {

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

	// Part
	public ArrayList<Part> getAllParts() {

		ArrayList<Part> partsResult = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Parts> partRoot = criteriaQuery.from(Parts.class);
			Root<PartGroups> partsGroupRoot = criteriaQuery.from(PartGroups.class);
			Root<Manufacturers> manufacturerRoot = criteriaQuery.from(Manufacturers.class);
			Root<Areas> areaRoot = criteriaQuery.from(Areas.class);
			Root<Racks> rackRoot = criteriaQuery.from(Racks.class);
			Root<Shelfs> shelfRoot = criteriaQuery.from(Shelfs.class);
			Root<Units> unitRoot = criteriaQuery.from(Units.class);
			Root<Users> userRoot = criteriaQuery.from(Users.class);
			
			//Subquery<Integer> subQuery1 = criteriaQuery.subquery(Integer.class);
			//Root<JobTitles> jobTitleRoot = subQuery1.from(JobTitles.class);
			//SetJoin<JobTitles, Users> subJobTitles =  jobTitleRoot.join(Users_.class);
			

			criteriaQuery.multiselect(partRoot, partsGroupRoot, manufacturerRoot, areaRoot, rackRoot, shelfRoot, unitRoot, userRoot);

			criteriaQuery.where(criteriaBuilder.and(
					criteriaBuilder.and(
							criteriaBuilder.equal(partRoot.get("partGroups"), partsGroupRoot.get("partGroupId")),
							criteriaBuilder.equal(partRoot.get("manufacturers"), manufacturerRoot.get("manufacturerId")),
							criteriaBuilder.equal(partRoot.get("areas"), areaRoot.get("areaId")),
							criteriaBuilder.equal(partRoot.get("racks"), rackRoot.get("rackId")),
							criteriaBuilder.equal(partRoot.get("shelfs"), shelfRoot.get("shelfId")),
							criteriaBuilder.equal(partRoot.get("units"), unitRoot.get("unitId")),
							criteriaBuilder.equal(partRoot.get("users"), userRoot.get("userId")))));

			TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

			List<Object[]> queryResult = query.getResultList();

			for (Object[] objects : queryResult) {

				Parts p = (Parts) objects[0];
				PartGroups pg = (PartGroups) objects[1];
				Manufacturers m = (Manufacturers) objects[2];
				Areas a = (Areas) objects[3];
				Racks r = (Racks) objects[4];
				Shelfs s = (Shelfs) objects[5];
				Units un = (Units) objects[6];
				Users us = (Users) objects[7];
				
				Part part = new Part();

				part.setPartId(p.getPartId());
				part.setArea(a.getArea());
				part.setManufacturer(m.getManufacturer());
				part.setPartGroup(pg.getPartGroup());
				part.setRack(r.getRack());
				part.setShelf(s.getShelf());
				part.setUnit(un.getUnit());
				part.setUser(us.getFirstName() + " " + us.getLastName());
				part.setOrderCode(p.getOrderCode());
				part.setProductCode(p.getProductCode());
				part.setPartName(p.getPartName());
				part.setDescription(p.getDescription());
				part.setQuantityMin(p.getQuantityMin());
				part.setQuantityMax(p.getQuantityMax());
				part.setCreationDate(p.getCreationDate());
				part.setLastChangeDate(p.getLastChangeDate());
				part.setImage(p.getImage());
			
				partsResult.add(part);
			}

			destroyManager();

			return partsResult;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}	

}
