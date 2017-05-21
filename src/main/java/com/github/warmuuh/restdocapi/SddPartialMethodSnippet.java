package com.github.warmuuh.restdocapi;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.snippet.TemplatedSnippet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.warmuuh.restdocapi.SddDocument.Method;
import com.github.warmuuh.restdocapi.SddDocument.Resource;

public class SddPartialMethodSnippet extends JsonAbstractSnippet<SddDocument> {

	private String methodDescription;

	protected SddPartialMethodSnippet(String methodDescription) {
		super("sdd");
		this.methodDescription = methodDescription;
	}

	@Override
	protected SddDocument createModel(Operation operation) {
		
		SddDocument doc = new SddDocument();
		doc.setResources(new LinkedList<SddDocument.Resource>());
		Resource res = new Resource();
		doc.getResources().add(res);
		res.setMethods(new LinkedList<SddDocument.Method>());
		
		Method m = new Method();
		res.getMethods().add(m);
		
		m.setId(operation.getName());
		m.setPath(operation.getRequest().getUri().getPath());
		m.setHttpMethod(operation.getRequest().getMethod().name());
		m.setDescription(methodDescription);
		
		return doc;
	}


}
