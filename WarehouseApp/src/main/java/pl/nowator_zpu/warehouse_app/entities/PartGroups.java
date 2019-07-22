package pl.nowator_zpu.warehouse_app.entities;
// Generated 19 lip 2019, 19:08:26 by Hibernate Tools 5.2.12.Final

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
 * PartGroups generated by hbm2java
 */
@Entity
@Table(name = "part_groups", catalog = "db_warehouse", uniqueConstraints = @UniqueConstraint(columnNames = "part_group"))
public class PartGroups implements java.io.Serializable {

	private Integer partGroupId;
	private String partGroup;
	private Set<Parts> parts = new HashSet<Parts>(0);

	public PartGroups() {
	}

	public PartGroups(String partGroup) {
		this.partGroup = partGroup;
	}

	public PartGroups(String partGroup, Set<Parts> parts) {
		this.partGroup = partGroup;
		this.parts = parts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "part_group_id", unique = true, nullable = false)
	public Integer getPartGroupId() {
		return this.partGroupId;
	}

	public void setPartGroupId(Integer partGroupId) {
		this.partGroupId = partGroupId;
	}

	@Column(name = "part_group", unique = true, nullable = false, length = 100)
	public String getPartGroup() {
		return this.partGroup;
	}

	public void setPartGroup(String partGroup) {
		this.partGroup = partGroup;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partGroups")
	public Set<Parts> getParts() {
		return this.parts;
	}

	public void setParts(Set<Parts> parts) {
		this.parts = parts;
	}

}
