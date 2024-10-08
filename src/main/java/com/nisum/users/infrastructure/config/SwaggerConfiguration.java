package com.nisum.users.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
	@Value("${info.app.name}")
	private String appName;
	@Value("${info.app.description}")
	private String appDescription;
	@Value("${info.app.version}")
	private String appVersion;
	@Value("${info.app.artifactId}")
	private String appArtifactId;

	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Development");
		return new OpenAPI().info(getApiInfo()).servers(List.of(server))
				.addSecurityItem(new SecurityRequirement().
						addList("Bearer Authentication"))
				.components(new Components().addSecuritySchemes
						("Bearer Authentication", createAPIKeyScheme()));
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.HTTP)
				.bearerFormat("JWT")
				.scheme("bearer");
	}

	private Info getApiInfo() {
		Contact myContact = new Contact();
		myContact.setName("Victor Puyo");
		myContact.setEmail("victoralfonsopuyo@gmail.com");

		Info information = new Info()
				.title(appName)
				.version(appVersion)
				.description(appDescription)
				.contact(myContact);
		return information;
	}
}