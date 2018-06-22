package com.mpm.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
		@Autowired
		private ResourceServerTokenServices tokenServices;
		public static final String RESOURCE_ID = "api";

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(RESOURCE_ID).tokenServices(tokenServices);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().antMatchers("/api/**").and().authorizeRequests().anyRequest().authenticated();
		}
	}

	

	@Configuration
	@EnableSwagger2
	protected static class Swagger2 {
		@Bean
		public Docket createRestApi() {
			return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
					.apis(RequestHandlerSelectors.basePackage("com.mpm")).paths(PathSelectors.any()).build()
					.globalOperationParameters(oauth2Header());
		}

		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("sso-resapi APIs").description("sso-resapi APIs")
					.termsOfServiceUrl("www.mpm.com").contact("mpm").version("1.0").build();
		}

		private List<Parameter> oauth2Header() {
			ParameterBuilder tokenpb = new ParameterBuilder();
			List<Parameter> pars = new ArrayList<Parameter>();
			tokenpb.name("Authorization").description("Oauth2 Bearer Token").modelRef(new ModelRef("String"))
					.parameterType("header").required(false);
			pars.add(tokenpb.build());
			return pars;
		}
	}
}
