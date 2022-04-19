package com.revature.dto;

import java.util.Objects;

import com.revature.models.TdModels;


public class TDto{
	
	private int modelId;
	
	private String modelName;
	
	private String Description;
	
	private UserDto creator;
	


	public TDto(TdModels t) {
		super();
		modelId = t.getModelId();
		modelName = t.getModelName();
		Description = t.getDescription();
		creator = t.getCreator();
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
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Description, creator, modelId, modelName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TDto other = (TDto) obj;
		return Objects.equals(Description, other.Description) && Objects.equals(creator, other.creator)
				&& modelId == other.modelId && Objects.equals(modelName, other.modelName);
	}

	@Override
	public String toString() {
		return "TDto [modelId=" + modelId + ", modelName=" + modelName + ", Description=" + Description + ", creator="
				+ creator + "]";
	}




}