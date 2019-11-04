package pl.nowator_zpu.warehouse_app.application_classes;

import java.util.Arrays;
import java.util.Date;

public class Part {
	
	private Integer partId;
	private String area;
	private String manufacturer;
	private String partGroup;
	private String rack;
	private String shelf;
	private String unit;
	private String user;
	private String orderCode;
	private String productCode;
	private String partName;
	private String description;
	private String link;
	private Integer quantityMin;
	private Integer quantityMax;
	private Date creationDate;
	private Date lastChangeDate;
	private byte[] image;
	
	public Part(Integer partId, String area, String manufacturer, String partGroup, String rack, String shelf,
			String unit, String user, String orderCode, String productCode, String partName, String description,
			String link, Integer quantityMin, Integer quantityMax, Date creationDate, Date lastChangeDate, byte[] image) {
		super();
		this.partId = partId;
		this.area = area;
		this.manufacturer = manufacturer;
		this.partGroup = partGroup;
		this.rack = rack;
		this.shelf = shelf;
		this.unit = unit;
		this.user = user;
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
	}

	public Part() {		
		this.partId = 0;
		this.area = "";
		this.manufacturer = "";
		this.partGroup = "";
		this.rack = "";
		this.shelf = "";
		this.unit = "";
		this.user = "";
		this.orderCode = "";
		this.productCode = "";
		this.partName = "";
		this.description = "";
		this.link = "";
		this.quantityMin = 0;
		this.quantityMax = 0;
		this.creationDate = null;
		this.lastChangeDate = null;
		this.image = null;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPartGroup() {
		return partGroup;
	}

	public void setPartGroup(String partGroup) {
		this.partGroup = partGroup;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
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

	public Integer getQuantityMin() {
		return quantityMin;
	}

	public void setQuantityMin(Integer quantityMin) {
		this.quantityMin = quantityMin;
	}

	public Integer getQuantityMax() {
		return quantityMax;
	}

	public void setQuantityMax(Integer quantityMax) {
		this.quantityMax = quantityMax;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((lastChangeDate == null) ? 0 : lastChangeDate.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((orderCode == null) ? 0 : orderCode.hashCode());
		result = prime * result + ((partGroup == null) ? 0 : partGroup.hashCode());
		result = prime * result + ((partId == null) ? 0 : partId.hashCode());
		result = prime * result + ((partName == null) ? 0 : partName.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((quantityMax == null) ? 0 : quantityMax.hashCode());
		result = prime * result + ((quantityMin == null) ? 0 : quantityMin.hashCode());
		result = prime * result + ((rack == null) ? 0 : rack.hashCode());
		result = prime * result + ((shelf == null) ? 0 : shelf.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		Part other = (Part) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (lastChangeDate == null) {
			if (other.lastChangeDate != null)
				return false;
		} else if (!lastChangeDate.equals(other.lastChangeDate))
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
		if (partGroup == null) {
			if (other.partGroup != null)
				return false;
		} else if (!partGroup.equals(other.partGroup))
			return false;
		if (partId == null) {
			if (other.partId != null)
				return false;
		} else if (!partId.equals(other.partId))
			return false;
		if (partName == null) {
			if (other.partName != null)
				return false;
		} else if (!partName.equals(other.partName))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (quantityMax == null) {
			if (other.quantityMax != null)
				return false;
		} else if (!quantityMax.equals(other.quantityMax))
			return false;
		if (quantityMin == null) {
			if (other.quantityMin != null)
				return false;
		} else if (!quantityMin.equals(other.quantityMin))
			return false;
		if (rack == null) {
			if (other.rack != null)
				return false;
		} else if (!rack.equals(other.rack))
			return false;
		if (shelf == null) {
			if (other.shelf != null)
				return false;
		} else if (!shelf.equals(other.shelf))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
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
		return "Part [partId=" + partId + ", area=" + area + ", manufacturer=" + manufacturer + ", partGroup="
				+ partGroup + ", rack=" + rack + ", shelf=" + shelf + ", unit=" + unit + ", user=" + user
				+ ", orderCode=" + orderCode + ", productCode=" + productCode + ", partName=" + partName
				+ ", description=" + description + ", link=" + link + ", quantityMin=" + quantityMin + ", quantityMax="
				+ quantityMax + ", creationDate=" + creationDate + ", lastChangeDate=" + lastChangeDate + ", image="
				+ Arrays.toString(image) + "]";
	}
	
}