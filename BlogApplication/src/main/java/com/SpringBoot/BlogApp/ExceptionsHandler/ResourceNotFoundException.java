package com.SpringBoot.BlogApp.ExceptionsHandler;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String resourceFieldName;
	long resourceFieldValue;
	
	public ResourceNotFoundException(String resourceName, String resourceFieldName, long resourceFieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,resourceFieldName,resourceFieldValue));
		this.resourceName = resourceName;
		this.resourceFieldName = resourceFieldName;
		this.resourceFieldValue = resourceFieldValue;
	}
	
}
