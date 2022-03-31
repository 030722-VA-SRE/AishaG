package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	@Column
	private LocalDate date;

	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private User creator;
	
	
	public TdModels() {
		super();
	}

	public TdModels(int i, String string, String string2, double d, User user) {
		i = id; 
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
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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
	
	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, date, description, id, modelName, price);
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
		return Objects.equals(creator, other.creator) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(modelName, other.modelName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}


	
	@Override
	public String toString() {
		return "TdModels [id=" + id + ", modelName=" + modelName + ", description=" + description + ", date=" + date
				+ ", price=" + price + ", creator=" + creator + "]";
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