package com.mpm.sso;

import java.security.Principal;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpm.sso.security.crypto.password.MpmPasswordEncoder;

@SpringBootApplication
@Controller
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping(value = "/")
	@ResponseBody
	public Principal me(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private DataSource dataSource;
		@Autowired
		private AuthenticationManager authenticationManager;
		@Autowired
		private RedisConnectionFactory connectionFactory;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// 使用JdbcClientDetailsService客户端详情服务
			clients.jdbc(dataSource);
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
			// .approvalStore(approvalStore()).authorizationCodeServices(authorizationCodeServices());
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			// 开启/oauth/token_key验证端口无权限访问
			// 开启/oauth/check_token验证端口认证权限访问
			security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}

		// @Bean
		// public ApprovalStore approvalStore() {
		// return null;
		// }

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
			defaultTokenServices.setTokenStore(tokenStore());
			// defaultTokenServices.setSupportRefreshToken(false);
			defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
			return defaultTokenServices;
		}

		@Bean
		public TokenStore tokenStore() {
			return new RedisTokenStore(connectionFactory);
		}
		// @Bean
		// public AuthorizationCodeServices authorizationCodeServices() {
		// // TODO Auto-generated method stub
		// return null;
		// }
	}

	// @Configuration
	// @EnableResourceServer
	// protected static class ResourceServerConfig extends
	// ResourceServerConfigurerAdapter {
	//
	// }

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new MpmPasswordEncoder());
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.headers().frameOptions().disable().and().csrf().disable();
			http.authorizeRequests().anyRequest().authenticated();
			http.formLogin().loginPage("/login").permitAll().and().logout().logoutUrl("/logout")
					.addLogoutHandler(new LogoutHandler() {

						@Override
						public void logout(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) {
							String userid = null;
							String url = request.getParameter("redirect_uri");
							if (authentication != null) {
								userid = authentication.getName();
							} else if (SecurityContextHolder.getContext().getAuthentication() != null) {
								UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
										.getAuthentication().getPrincipal();
								userid = userDetails.getUsername();
							}

							Enumeration em = request.getSession().getAttributeNames();
							while (em.hasMoreElements()) {
								request.getSession().removeAttribute(em.nextElement().toString());
							}
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
					}).permitAll();

		}

	}
}
