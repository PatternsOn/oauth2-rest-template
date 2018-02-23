package com.patternson.oauth2resttemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class Oauth2RestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2RestTemplateApplication.class, args);
	}

	@Bean
	public SecurityBatch batch() {
		return new SecurityBatch();
	}

	class SecurityBatch implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
			resource.setAccessTokenUri("http://localhost:9000/service/oauth/token");
			resource.setClientId("springdeveloper123");
			resource.setClientSecret("secret");
			resource.setGrantType("password");
			resource.setUsername("allen");
			resource.setPassword("password");
			resource.setClientAuthenticationScheme(AuthenticationScheme.header);
			OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
			String results = template.getForObject("http://localhost:8000/resource/account", String.class);
			System.out.println("results : " + results);
		}
	}
}
