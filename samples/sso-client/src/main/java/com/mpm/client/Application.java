package com.mpm.client;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@SpringBootApplication
@RestController
public class Application {
	@RequestMapping(value = "/")
	public String me(Principal principal) {
		if(principal instanceof OAuth2Authentication) {
			OAuth2Authentication oauth = (OAuth2Authentication)principal;
			String token = "";
			if(oauth.getDetails() instanceof OAuth2AuthenticationDetails) {
				OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails)oauth.getDetails();
				token = detail.getTokenValue();
			}
			return "hello,token:"+token+",username:"+oauth.getPrincipal();
		}
		return JSON.toJSONString(principal);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	protected OAuth2RestTemplate OAuth2RestTemplate(OAuth2ProtectedResourceDetails resource,
			OAuth2ClientContext context) {
		return new OAuth2RestTemplate(resource, context);
	}

	@Configuration
	@EnableOAuth2Sso
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.headers().frameOptions().disable();
			http.csrf().disable();
			http.authorizeRequests().antMatchers("/images/**", "/jslib/**", "/style/**").permitAll().anyRequest()
					.authenticated();
			http.logout().addLogoutHandler(new LogoutHandler() {

				@Override
				public void logout(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) {
					request.getSession().invalidate();
					SecurityContext context = SecurityContextHolder.getContext();
					context.setAuthentication(null);
					SecurityContextHolder.clearContext();
					Cookie[] cookies = request.getCookies();
					for (Cookie ck : cookies) {
						ck.setMaxAge(0);
						ck.setValue(null);
						response.addCookie(ck);
					}

				}
			});

		}

	}
}
