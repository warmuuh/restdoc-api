package com.github.warmuuh.restdocapi;

public class SddDocumentation {

	public static SddPartialMethodSnippet sddMethod(String description){
		return new SddPartialMethodSnippet(description);
	}
}
