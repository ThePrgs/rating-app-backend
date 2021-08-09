package com.nsoft.ratingappbackend.swagger;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig class is used for Swagger API setup.
 */
@Configuration
public class SwaggerConfig {


	/**
	 * Method generates documentation with described RESTful APIs using Swagger IDL.
	 *
	 * @return Docket of type SWAGGER_2.
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(PathSelectors.ant("/api/**"))
			.apis(RequestHandlerSelectors.basePackage("com.nsoft"))
			.build().apiInfo(apiDetails());

	}

	/**
	 * Method sets basic api details.
	 *
	 * @return ApiInfo.
	 */
	private ApiInfo apiDetails() {
		return new ApiInfo(
			"Rating app API",
			"API-s used for rating app",
			"1.0",
			"Free to use",
			new springfox.documentation.service.Contact("NSoft", "https://www.nsoft.com/",
				"info@nsoft.com"),
			"API Licence",
			"https://www.nsoft.com/",
			Collections.emptyList());
	}

}
