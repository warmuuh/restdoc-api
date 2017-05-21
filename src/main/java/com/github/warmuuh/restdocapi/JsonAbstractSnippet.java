package com.github.warmuuh.restdocapi;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.restdocs.RestDocumentationContext;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.snippet.WriterResolver;
import org.springframework.restdocs.templates.TemplateEngine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class JsonAbstractSnippet<T> implements Snippet {

	
	
	private String snippetName;

	
	public JsonAbstractSnippet(String snippetName) {
		super();
		this.snippetName = snippetName;
	}

	@Override
	public void document(Operation operation) throws IOException {
		RestDocumentationContext context = (RestDocumentationContext) operation
				.getAttributes().get(RestDocumentationContext.class.getName());
		WriterResolver writerResolver = (WriterResolver) operation.getAttributes()
				.get(WriterResolver.class.getName());
		try (Writer writer = writerResolver.resolve(operation.getName(), this.snippetName,
				context)) {
			T model = createModel(operation);
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(writer, model);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	abstract protected T createModel(Operation operation);

}
