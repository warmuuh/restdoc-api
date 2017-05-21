package com.github.warmuuh.restdocapi;

import static org.junit.Assert.fail;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.restassured3.operation.preprocess.RestAssuredPreprocessors.modifyUris;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.restdocs.JUnitRestDocumentation;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.github.warmuuh.restdocapi.SddDocumentation.*;

public class BaseDocTest {
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	private RequestSpecification documentationSpec;

	@Before
	public void setUp() {
		this.documentationSpec = new RequestSpecBuilder()
				.addFilter(documentationConfiguration(restDocumentation)
						.snippets().withDefaults().withTemplateFormat(new SddTemplateFormat()))
				.setBaseUri("http://ip.jsontest.com")
				.build();
	}

	@Test
	public void test() {
		given(this.documentationSpec)
		.accept("text/plain")
		.filter(document("sample", sddMethod("sample sdd resource documentation")))
		.when()
				.get("/")
		.then()
				.assertThat().statusCode(is(200));
	
	}

}
