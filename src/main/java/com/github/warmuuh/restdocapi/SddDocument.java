package com.github.warmuuh.restdocapi;

import java.util.List;

import lombok.Data;

@Data
public class SddDocument {

	List<Resource> resources;
	
	
	@Data
	public static class Resource {
		List<Method> methods;
	}
	
	@Data
	public static class Method {
		String id;
		String path;
		String httpMethod;
		String description;
	}
}
