package eu.ag.br.booking.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.ag.br.booking.common.BRAGLogger;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author MOwsians
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final BRAGLogger logger = BRAGLogger.newInstance(SwaggerConfig.class);

	private static final String API_TITLE = "Booking of table API";
	private static final String API_DESCRIPTION = "Simple CRUD. ";
	private static final String API_VERSION = "1.0.0";
	private static final String API_TERMS_OF_SERVICE_URL = "";
	private static final String API_LICENSE = "";
	private static final String API_LICENSE_URL = "";

	/**
	 * Springfox bean for swagger settings.
	 * 
	 * @return
	 */
	@Bean
	public Docket swaggerSettings() {
		logger.info("Setting up swagger...");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("eu.ag.br.booking.ws.rest.controller"))
				.paths(PathSelectors.any())
				.build().pathMapping("/").apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		logger.info("Api version: " + API_VERSION);
		return new ApiInfo(API_TITLE, API_DESCRIPTION, 
						   API_VERSION, 
						   API_TERMS_OF_SERVICE_URL, 
						   new Contact("Mateusz Owsianski", "", "m.owsianski@gmail.com"), 
						   API_LICENSE,
						   API_LICENSE_URL, Collections.emptyList());
	}

}
