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

		ArrayList<Part> result = new ArrayList<>();

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
			userRoot.join("jobTitles");
			userRoot.join("userRights");

			criteriaQuery.multiselect(partRoot, partsGroupRoot, manufacturerRoot, areaRoot, rackRoot, shelfRoot,
					unitRoot, userRoot);

			criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.and(
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

				result.add(part);
			}

			destroyManager();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Part getPartByOrderCodeAndManufacturerId(String orderCode, Integer manufacturerId) {

		Part result = new Part();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Parts> partRoot = criteriaQuery.from(Parts.class);

			Path<String> c = partRoot.get("orderCode");
			Path<Integer> m = partRoot.get("manufacturers");

			Root<PartGroups> partsGroupRoot = criteriaQuery.from(PartGroups.class);
			Root<Manufacturers> manufacturerRoot = criteriaQuery.from(Manufacturers.class);
			Root<Areas> areaRoot = criteriaQuery.from(Areas.class);
			Root<Racks> rackRoot = criteriaQuery.from(Racks.class);
			Root<Shelfs> shelfRoot = criteriaQuery.from(Shelfs.class);
			Root<Units> unitRoot = criteriaQuery.from(Units.class);

			Root<Users> userRoot = criteriaQuery.from(Users.class);
			userRoot.join("jobTitles");
			userRoot.join("userRights");

			criteriaQuery.multiselect(partRoot, partsGroupRoot, manufacturerRoot, areaRoot, rackRoot, shelfRoot,
					unitRoot, userRoot);

			criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.and(
					criteriaBuilder.equal(partRoot.get("partGroups"), partsGroupRoot.get("partGroupId")),
					criteriaBuilder.equal(partRoot.get("manufacturers"), manufacturerRoot.get("manufacturerId")),
					criteriaBuilder.equal(partRoot.get("areas"), areaRoot.get("areaId")),
					criteriaBuilder.equal(partRoot.get("racks"), rackRoot.get("rackId")),
					criteriaBuilder.equal(partRoot.get("shelfs"), shelfRoot.get("shelfId")),
					criteriaBuilder.equal(partRoot.get("units"), unitRoot.get("unitId")),
					criteriaBuilder.equal(partRoot.get("users"), userRoot.get("userId")),
					criteriaBuilder.equal(c, orderCode)), (criteriaBuilder.equal(m, manufacturerId))));

			TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

			Object[] queryResult = query.getSingleResult();

			Parts p = (Parts) queryResult[0];
			PartGroups pg = (PartGroups) queryResult[1];
			Manufacturers mr = (Manufacturers) queryResult[2];
			Areas a = (Areas) queryResult[3];
			Racks r = (Racks) queryResult[4];
			Shelfs s = (Shelfs) queryResult[5];
			Units un = (Units) queryResult[6];
			Users us = (Users) queryResult[7];

			result.setPartId(p.getPartId());
			result.setArea(a.getArea());
			result.setManufacturer(mr.getManufacturer());
			result.setPartGroup(pg.getPartGroup());
			result.setRack(r.getRack());
			result.setShelf(s.getShelf());
			result.setUnit(un.getUnit());
			result.setUser(us.getFirstName() + " " + us.getLastName());
			result.setOrderCode(p.getOrderCode());
			result.setProductCode(p.getProductCode());
			result.setPartName(p.getPartName());
			result.setDescription(p.getDescription());
			result.setQuantityMin(p.getQuantityMin());
			result.setQuantityMax(p.getQuantityMax());
			result.setCreationDate(p.getCreationDate());
			result.setLastChangeDate(p.getLastChangeDate());
			result.setImage(p.getImage());

			destroyManager();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Boolean newPart(Parts part) {

		try {

			createManager();

			em.getTransaction().begin();
			em.persist(part);
			em.getTransaction().commit();

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	public Boolean changePart(Parts part) {

		try {

			createManager();

			em.getTransaction().begin();
			em.merge(part);
			em.getTransaction().commit();

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	public Boolean deletePartById(int partId) {

		try {

			createManager();

			Parts p = em.find(Parts.class, partId);

			if (p != null) {
				em.getTransaction().begin();
				em.remove(p);
				em.getTransaction().commit();
			}

			destroyManager();

			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return false;
		}
	}

	// Area
	public ArrayList<String> getAllAreas() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Areas> criteriaQuery = criteriaBuilder.createQuery(Areas.class);
			Root<Areas> areaRoot = criteriaQuery.from(Areas.class);

			criteriaQuery.select(areaRoot);
			TypedQuery<Areas> query = em.createQuery(criteriaQuery);

			List<Areas> queryResult = query.getResultList();

			for (Areas a : queryResult) {
				result.add(a.getArea());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Areas getAreaByArea(String area) {

		Areas result = new Areas();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Areas> criteriaQuery = criteriaBuilder.createQuery(Areas.class);
			Root<Areas> areaRoot = criteriaQuery.from(Areas.class);

			Path<String> a = areaRoot.get("area");

			criteriaQuery.select(areaRoot);

			criteriaQuery.where(criteriaBuilder.equal(a, area));

			TypedQuery<Areas> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Manufacturer
	public ArrayList<String> getAllManufacturers() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Manufacturers> criteriaQuery = criteriaBuilder.createQuery(Manufacturers.class);
			Root<Manufacturers> manufacturerRoot = criteriaQuery.from(Manufacturers.class);

			criteriaQuery.select(manufacturerRoot);
			TypedQuery<Manufacturers> query = em.createQuery(criteriaQuery);

			List<Manufacturers> queryResult = query.getResultList();

			for (Manufacturers m : queryResult) {
				result.add(m.getManufacturer());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Manufacturers getManufacturerByManufacturer(String manufacturer) {

		Manufacturers result = new Manufacturers();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Manufacturers> criteriaQuery = criteriaBuilder.createQuery(Manufacturers.class);
			Root<Manufacturers> manufacturerRoot = criteriaQuery.from(Manufacturers.class);

			Path<String> m = manufacturerRoot.get("manufacturer");

			criteriaQuery.select(manufacturerRoot);

			criteriaQuery.where(criteriaBuilder.equal(m, manufacturer));

			TypedQuery<Manufacturers> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Part group
	public ArrayList<String> getAllPartGroups() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<PartGroups> criteriaQuery = criteriaBuilder.createQuery(PartGroups.class);
			Root<PartGroups> partGroupRoot = criteriaQuery.from(PartGroups.class);

			criteriaQuery.select(partGroupRoot);
			TypedQuery<PartGroups> query = em.createQuery(criteriaQuery);

			List<PartGroups> queryResult = query.getResultList();

			for (PartGroups pg : queryResult) {
				result.add(pg.getPartGroup());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public PartGroups getPartGroupByPartGroup(String group) {

		PartGroups result = new PartGroups();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<PartGroups> criteriaQuery = criteriaBuilder.createQuery(PartGroups.class);
			Root<PartGroups> partGroupRoot = criteriaQuery.from(PartGroups.class);

			Path<String> pg = partGroupRoot.get("partGroup");

			criteriaQuery.select(partGroupRoot);

			criteriaQuery.where(criteriaBuilder.equal(pg, group));

			TypedQuery<PartGroups> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Rack
	public ArrayList<Integer> getAllRacks() {

		ArrayList<Integer> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Racks> criteriaQuery = criteriaBuilder.createQuery(Racks.class);
			Root<Racks> rackRoot = criteriaQuery.from(Racks.class);

			criteriaQuery.select(rackRoot);
			TypedQuery<Racks> query = em.createQuery(criteriaQuery);

			List<Racks> queryResult = query.getResultList();

			for (Racks r : queryResult) {
				result.add(r.getRack());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Racks getRackByRack(String rack) {

		Racks result = new Racks();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Racks> criteriaQuery = criteriaBuilder.createQuery(Racks.class);
			Root<Racks> rackRoot = criteriaQuery.from(Racks.class);

			Path<String> r = rackRoot.get("rack");

			criteriaQuery.select(rackRoot);

			criteriaQuery.where(criteriaBuilder.equal(r, rack));

			TypedQuery<Racks> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Shelf
	public ArrayList<Integer> getAllShelfs() {

		ArrayList<Integer> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Shelfs> criteriaQuery = criteriaBuilder.createQuery(Shelfs.class);
			Root<Shelfs> shelfRoot = criteriaQuery.from(Shelfs.class);

			criteriaQuery.select(shelfRoot);
			TypedQuery<Shelfs> query = em.createQuery(criteriaQuery);

			List<Shelfs> queryResult = query.getResultList();

			for (Shelfs s : queryResult) {
				result.add(s.getShelf());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Shelfs getShelfByShelf(String shelf) {

		Shelfs result = new Shelfs();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Shelfs> criteriaQuery = criteriaBuilder.createQuery(Shelfs.class);
			Root<Shelfs> shelfRoot = criteriaQuery.from(Shelfs.class);

			Path<String> s = shelfRoot.get("shelf");

			criteriaQuery.select(shelfRoot);

			criteriaQuery.where(criteriaBuilder.equal(s, shelf));

			TypedQuery<Shelfs> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Unit
	public ArrayList<String> getAllUnits() {

		ArrayList<String> result = new ArrayList<>();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Units> criteriaQuery = criteriaBuilder.createQuery(Units.class);
			Root<Units> unitRoot = criteriaQuery.from(Units.class);

			criteriaQuery.select(unitRoot);
			TypedQuery<Units> query = em.createQuery(criteriaQuery);

			List<Units> queryResult = query.getResultList();

			for (Units u : queryResult) {
				result.add(u.getUnit());
			}

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	public Units getUnitByUnit(String unit) {

		Units result = new Units();

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Units> criteriaQuery = criteriaBuilder.createQuery(Units.class);
			Root<Units> unitRoot = criteriaQuery.from(Units.class);

			Path<String> u = unitRoot.get("unit");

			criteriaQuery.select(unitRoot);

			criteriaQuery.where(criteriaBuilder.equal(u, unit));

			TypedQuery<Units> query = em.createQuery(criteriaQuery);

			result = query.getSingleResult();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}

	// Image
	public byte[] getImageByOrderCodeAndManufacturerId(String code, Integer manufacturerId) {

		Parts part = new Parts();
		byte[] result = null;

		try {

			createManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Parts> criteriaQuery = criteriaBuilder.createQuery(Parts.class);
			Root<Parts> partsRoot = criteriaQuery.from(Parts.class);

			Path<String> c = partsRoot.get("orderCode");
			Path<Integer> m = partsRoot.get("manufacturers");

			criteriaQuery.select(partsRoot);

			criteriaQuery.where(
					criteriaBuilder.and((criteriaBuilder.equal(c, code)), (criteriaBuilder.equal(m, manufacturerId))));

			TypedQuery<Parts> query = em.createQuery(criteriaQuery);

			part = query.getSingleResult();

			result = part.getImage();

			return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
			return null;
		}
	}
}
