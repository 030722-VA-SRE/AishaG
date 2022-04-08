package com.revature.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.dto.UserDto;
import com.revature.exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor



@Entity
@Table(name="tdmodels")
@Builder
public class TdModels {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int modelId;
	
	@Column(nullable = false)
	private String modelName;
	
	@Column(nullable = false)
	private String description;

	@Column(name="creation_date", updatable=false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private LocalDate dateCreated;

	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private User creator;

}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getModelName() {
//		return modelName;
//	}
//
//	public void setModelName(String modelName) {
//		this.modelName = modelName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public LocalDate getCurrentDate() {
//		return currentDate;
//	}
//
//	public void setCurrentDate(LocalDate date) {
//		this.currentDate = date;
//	}
//
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
//	public User getCreator() {
//		
//		return creator;
//	}
//}
//	
//	public void setCreator(UserDto user) throws UserNotFoundException{
//		
//		 this.user;
//	}
//}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(modelName, currentDate, description, id, price, creator);
//	}
//
//	//dateObtained, isOwner, modelType
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TdModels other = (TdModels) obj;
//		return Objects.equals(modelName, other.modelName) &&
//				Objects.equals(currentDate, other.currentDate) && Objects.equals(description, other.description)
//				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && Objects.equals(creator,  other.creator);
//	}
//
//
//	
//	@Override
//	public String toString() {
//		return "TdModels [id=" + id + ", modelName=" + modelName +  ", description=" + description
//				+ ", currentDate=" + currentDate +  ", userAssigned=" + creator + ", price=" + price + "]";
//	}
//
//
//}
//

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