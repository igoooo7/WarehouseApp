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
 * Areas generated by hbm2java
 */
@Entity
@Table(name = "areas", catalog = "db_warehouse", uniqueConstraints = @UniqueConstraint(columnNames = "area"))
public class Areas implements java.io.Serializable {

	private Integer areaId;
	private String area;
	private Set<Parts> parts = new HashSet<Parts>(0);

	public Areas() {
	}

	public Areas(String area) {
		this.area = area;
	}

	public Areas(String area, Set<Parts> parts) {
		this.area = area;
		this.parts = parts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "area_id", unique = true, nullable = false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "area", unique = true, nullable = false, length = 100)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "areas")
	public Set<Parts> getParts() {
		return this.parts;
	}

	public void setParts(Set<Parts> parts) {
		this.parts = parts;
	}

}