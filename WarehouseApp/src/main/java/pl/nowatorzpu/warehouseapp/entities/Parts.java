package pl.nowatorzpu.warehouseapp.entities;
// Generated 20 sie 2019, 07:51:47 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Parts generated by hbm2java
 */
@Entity
@Table(name = "parts", catalog = "db_warehouse")
public class Parts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer partId;
	private Areas areas;
	private Manufacturers manufacturers;
	private PartGroups partGroups;
	private Racks racks;
	private Shelfs shelfs;
	private Units units;
	private Users users;
	private String orderCode;
	private String productCode;
	private String partName;
	private String description;
	private String link;
	private int quantityMin;
	private int quantityMax;
	private Date creationDate;
	private Date lastChangeDate;
	private byte[] image;
	private Set<Orders> orders = new HashSet<Orders>(0);

	public Parts() {
	}

	public Parts(Areas areas, Manufacturers manufacturers, PartGroups partGroups, Racks racks, Shelfs shelfs,
			Units units, Users users, String orderCode, int quantityMin, int quantityMax) {
		this.areas = areas;
		this.manufacturers = manufacturers;
		this.partGroups = partGroups;
		this.racks = racks;
		this.shelfs = shelfs;
		this.units = units;
		this.users = users;
		this.orderCode = orderCode;
		this.quantityMin = quantityMin;
		this.quantityMax = quantityMax;
	}

	public Parts(Areas areas, Manufacturers manufacturers, PartGroups partGroups, Racks racks, Shelfs shelfs,
			Units units, Users users, String orderCode, String productCode, String partName, String description,
			String link, int quantityMin, int quantityMax, Date creationDate, Date lastChangeDate, byte[] image,
			Set<Orders> orders) {
		this.areas = areas;
		this.manufacturers = manufacturers;
		this.partGroups = partGroups;
		this.racks = racks;
		this.shelfs = shelfs;
		this.units = units;
		this.users = users;
		this.orderCode = orderCode;
		this.productCode = productCode;
		this.partName = partName;
		this.description = description;
		this.link = link;
		this.quantityMin = quantityMin;
		this.quantityMax = quantityMax;
		this.creationDate = creationDate;
		this.lastChangeDate = lastChangeDate;
		this.image = image;
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "part_id", unique = true, nullable = false)
	public Integer getPartId() {
		return this.partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", nullable = false)
	public Areas getAreas() {
		return this.areas;
	}

	public void setAreas(Areas areas) {
		this.areas = areas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	public Manufacturers getManufacturers() {
		return this.manufacturers;
	}

	public void setManufacturers(Manufacturers manufacturers) {
		this.manufacturers = manufacturers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part_group_id", nullable = false)
	public PartGroups getPartGroups() {
		return this.partGroups;
	}

	public void setPartGroups(PartGroups partGroups) {
		this.partGroups = partGroups;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id", nullable = false)
	public Racks getRacks() {
		return this.racks;
	}

	public void setRacks(Racks racks) {
		this.racks = racks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shelf_id", nullable = false)
	public Shelfs getShelfs() {
		return this.shelfs;
	}

	public void setShelfs(Shelfs shelfs) {
		this.shelfs = shelfs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", nullable = false)
	public Units getUnits() {
		return this.units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "order_code", nullable = false)
	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name = "product_code")
	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "part_name")
	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "link")
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "quantity_min", nullable = false)
	public int getQuantityMin() {
		return this.quantityMin;
	}

	public void setQuantityMin(int quantityMin) {
		this.quantityMin = quantityMin;
	}

	@Column(name = "quantity_max", nullable = false)
	public int getQuantityMax() {
		return this.quantityMax;
	}

	public void setQuantityMax(int quantityMax) {
		this.quantityMax = quantityMax;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_change_date", length = 19)
	public Date getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parts")
	public Set<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

}
