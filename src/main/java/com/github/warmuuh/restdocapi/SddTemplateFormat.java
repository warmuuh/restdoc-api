package com.github.warmuuh.restdocapi;

import org.springframework.restdocs.templates.TemplateFormat;

public class SddTemplateFormat implements TemplateFormat{

	public String getId() {
		return "sdd";
	}

	public String getFileExtension() {
		return "json";
	}

}
