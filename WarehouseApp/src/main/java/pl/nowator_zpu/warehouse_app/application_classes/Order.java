package pl.nowator_zpu.warehouse_app.application_classes;

import java.util.Date;

public class Order {

	private Integer orderId;
	private String orderCode;	
	private String manufacturer;
	private String description;
	private String link;
	private Integer partCount;
	private String user;
	private String project;
	private Date orderDate;
	
	public Order(Integer orderId, String orderCode, String manufacturer,String description, String link, Integer partCount, String user, String project,
			Date orderDate) {
		super();
		this.orderId = orderId;
		this.orderCode = orderCode;
		this.manufacturer = manufacturer;
		this.description = description;
		this.link = link;
		this.partCount = partCount;
		this.user = user;
		this.project = project;
		this.orderDate = orderDate;
	}
	
	public Order() {		 
		this.orderId = 0;
		this.orderCode = "";
		this.manufacturer = "";
		this.description = "";
		this.link = "";
		this.partCount = 0;
		this.user = "";
		this.project = "";
		this.orderDate = null;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getPartCount() {
		return partCount;
	}

	public void setPartCount(Integer partCount) {
		this.partCount = partCount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((orderCode == null) ? 0 : orderCode.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((partCount == null) ? 0 : partCount.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (orderCode == null) {
			if (other.orderCode != null)
				return false;
		} else if (!orderCode.equals(other.orderCode))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (partCount == null) {
			if (other.partCount != null)
				return false;
		} else if (!partCount.equals(other.partCount))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderCode=" + orderCode + ", manufacturer=" + manufacturer
				+ ", description=" + description + ", link=" + link + ", partCount=" + partCount + ", user=" + user
				+ ", project=" + project + ", orderDate=" + orderDate + "]";
	}
		
}
