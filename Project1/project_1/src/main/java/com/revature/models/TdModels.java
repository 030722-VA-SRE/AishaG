package com.revature.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="tdmodels")
public class TdModels {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String modelName;
	
	@Column(nullable = false)
	private String description;
//	/*-
//	 * LocalDate object can be serialized to a JSON string by using the following annotation and the
//	 * jackson-datatype-jsr310 dependency.
//	 */
	@Column(nullable = false)
	private Date currentDate;

	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private User creator;
	
	
	public TdModels() {
		super();
	}

	public TdModels(int i, String string, String string2, Date date, double d, User user) {
		i = id; 
		date = currentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date date) {
		this.currentDate = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getCreator() {
		return creator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentDate, description, id, modelName, price, creator);
	}

	//dateObtained, isOwner, modelType
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TdModels other = (TdModels) obj;
		return Objects.equals(currentDate, other.currentDate)
				&& Objects.equals(description, other.description) && Objects.equals(modelName, other.modelName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && Objects.equals(creator,  other.creator);
	}


	
	@Override
	public String toString() {
		return "TdModels [id=" + id + ", modelName=" + modelName +  ", description=" + description
				+ ", currentDate=" + currentDate +  ", userAssigned=" + creator + ", price=" + price + "]";
	}


}


//", modelType=" + modelType + ", dateObtained=" + dateObtained + ", isOwner=" + isOwner
//+

//public String getModelType() {
//return modelType;
//}
//
//public void setModelType(String modelType) {
//this.modelType = modelType;
//}
// && Objects.equals(dateObtained, other.dateObtained)  && id == other.id && isOwner == other.isOwner
//&& Objects.equals(modelType, other.modelType)
//public Date getDateObtained() {
//return dateObtained;
//}
//
//public void setDateObtained(Date date) {
//this.dateObtained = date;
//}
//
//public boolean isOwner() {
//return isOwner;
//}
//
//public void setOwner(boolean isOwner) {
//this.isOwner = isOwner;
//}
//private Date dateObtained;

//private boolean isOwner;
//private String modelType;