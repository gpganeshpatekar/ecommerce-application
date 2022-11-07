package com.ecom.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.info.Contact;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, "Authorization", "header");
    }

	 private SecurityContext securityContext() {
	        return SecurityContext.builder()
	                .securityReferences(defaultAuth())
	                .build();
	    }

	List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }
	
	@Bean
	public Docket getDocket() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInformation()).securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
	

	private ApiInfo getApiInformation() {
		// TODO Auto-generated method stub

		return new ApiInfo("API of ecommerce-app created while learning",
				"These API's are created by Gaensh Patekar, get more details below about API's", "1.0",
				"Terms of Services", "License of API's : Ganesh Patekar", "https://www.google.co.in/", "");
	}

}
