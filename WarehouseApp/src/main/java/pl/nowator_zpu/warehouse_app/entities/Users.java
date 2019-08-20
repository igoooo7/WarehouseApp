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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "db_warehouse", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class Users implements java.io.Serializable {

	private Integer userId;
	private JobTitles jobTitles;
	private UserRights userRights;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String firstName;
	private String lastName;
	private Set<Parts> parts = new HashSet<Parts>(0);
	private Set<Orders> orders = new HashSet<Orders>(0);

	public Users() {
	}

	public Users(JobTitles jobTitles, UserRights userRights, String userName) {
		this.jobTitles = jobTitles;
		this.userRights = userRights;
		this.userName = userName;
	}

	public Users(JobTitles jobTitles, UserRights userRights, String userName, String userPassword, String userEmail,
			String firstName, String lastName, Set<Parts> parts, Set<Orders> orders) {
		this.jobTitles = jobTitles;
		this.userRights = userRights;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.parts = parts;
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_title_id", nullable = false)
	public JobTitles getJobTitles() {
		return this.jobTitles;
	}

	public void setJobTitles(JobTitles jobTitles) {
		this.jobTitles = jobTitles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_rights_id", nullable = false)
	public UserRights getUserRights() {
		return this.userRights;
	}

	public void setUserRights(UserRights userRights) {
		this.userRights = userRights;
	}

	@Column(name = "user_name", unique = true, nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_password", length = 25)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_email", length = 50)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "first_name", length = 25)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 25)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Parts> getParts() {
		return this.parts;
	}

	public void setParts(Set<Parts> parts) {
		this.parts = parts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

}
