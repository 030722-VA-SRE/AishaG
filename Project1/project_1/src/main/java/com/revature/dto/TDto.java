package com.revature.dto;

import com.revature.models.TdModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
	}
}
//	
//	public int getModelId() {
//		return modelId;
//	}
//
//	public void setModelId(int modelId) {
//		this.modelId = modelId;
//	}
//
//	public String getModelName() {
//		return modelName;
//	}
//
//	public void setModelName(String modelName) {
//		this.modelName = modelName;
//	}
//
//	public String getDescription() {
//		return Description;
//	}
//
//	public void setDescription(String description) {
//		Description = description;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(Description, modelId, modelName);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TDto other = (TDto) obj;
//		return Objects.equals(Description, other.Description) && modelId == other.modelId
//				&& Objects.equals(modelName, other.modelName);
//	}
//
//	@Override
//	public String toString() {
//		return "TDto [modelId=" + modelId + ", modelName=" + modelName + ", Description=" + Description + "]";
//	}
//
//
//}