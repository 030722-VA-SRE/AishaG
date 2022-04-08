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

import com.revature.dto.UserDto;
import lombok.Builder;




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

	public TdModels() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TdModels(int modelId, String modelName, String description, LocalDate dateCreated, double price,
			User creator) {
		super();
		this.modelId = modelId;
		this.modelName = modelName;
		this.description = description;
		this.dateCreated = dateCreated;
		this.price = price;
		this.creator = creator;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
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

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public UserDto getCreator() {
		return new UserDto(creator);
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, dateCreated, description, modelId, modelName, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TdModels other = (TdModels) obj;
		return Objects.equals(creator, other.creator) && Objects.equals(dateCreated, other.dateCreated)
				&& Objects.equals(description, other.description) && modelId == other.modelId
				&& Objects.equals(modelName, other.modelName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "TdModels [modelId=" + modelId + ", modelName=" + modelName + ", description=" + description
				+ ", dateCreated=" + dateCreated + ", price=" + price + ", creator=" + creator + "]";
	}

}