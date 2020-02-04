package pl.nowatorzpu.warehouseapp.entities;
// Generated 20 sie 2019, 07:51:47 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Manufacturers generated by hbm2java
 */
@Entity
@Table(name = "manufacturers", catalog = "db_warehouse", uniqueConstraints = @UniqueConstraint(columnNames = "manufacturer"))
public class Manufacturers implements java.io.Serializable {

	private Integer manufacturerId;
	private String manufacturer;
	private Set<Parts> parts = new HashSet<Parts>(0);

	public Manufacturers() {
	}

	public Manufacturers(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Manufacturers(String manufacturer, Set<Parts> parts) {
		this.manufacturer = manufacturer;
		this.parts = parts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "manufacturer_id", unique = true, nullable = false)
	public Integer getManufacturerId() {
		return this.manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	@Column(name = "manufacturer", unique = true, nullable = false, length = 100)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturers")
	public Set<Parts> getParts() {
		return this.parts;
	}

	public void setParts(Set<Parts> parts) {
		this.parts = parts;
	}

}