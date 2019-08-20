package pl.nowator_zpu.warehouse_app.entities;
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
 * Racks generated by hbm2java
 */
@Entity
@Table(name = "racks", catalog = "db_warehouse", uniqueConstraints = @UniqueConstraint(columnNames = "rack"))
public class Racks implements java.io.Serializable {

	private Integer rackId;
	private int rack;
	private Set<Parts> parts = new HashSet<Parts>(0);

	public Racks() {
	}

	public Racks(int rack) {
		this.rack = rack;
	}

	public Racks(int rack, Set<Parts> parts) {
		this.rack = rack;
		this.parts = parts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "rack_id", unique = true, nullable = false)
	public Integer getRackId() {
		return this.rackId;
	}

	public void setRackId(Integer rackId) {
		this.rackId = rackId;
	}

	@Column(name = "rack", unique = true, nullable = false)
	public int getRack() {
		return this.rack;
	}

	public void setRack(int rack) {
		this.rack = rack;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "racks")
	public Set<Parts> getParts() {
		return this.parts;
	}

	public void setParts(Set<Parts> parts) {
		this.parts = parts;
	}

}
