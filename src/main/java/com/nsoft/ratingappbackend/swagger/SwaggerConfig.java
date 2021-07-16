package com.nsoft.ratingappbackend.swagger;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class SwaggerConfig {

	public static void main(String[] args){
		SpringApplication.run(SwaggerConfig.class,args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(PathSelectors.ant("/api/**"))
			.apis(RequestHandlerSelectors.basePackage("com.nsoft"))
			.build().apiInfo(apiDetails());

	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
			"Rating app API",
			"API-s used for rating app",
			"1.0",
			"Free to use",
			new springfox.documentation.service.Contact("NSoft", "https://www.nsoft.com/", "info@nsoft.com"),
			"API Licence",
			"https://www.nsoft.com/",
			Collections.emptyList());
	}

}
