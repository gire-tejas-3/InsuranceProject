package com.insurance.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.insurance.auth.service.AuthFilterService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthFilterService authFilterService;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	// Public URLs that do not require authentication
	public static String[] PUBLIC_URLS = { "/api/v1/auth/*", "/api/v1/auth/register", "/api/v1/auth/login",
			"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**" };

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class).logout(logout -> logout
						.logoutUrl("/api/v1/auth/logout").logoutSuccessHandler((req, res, authentication) -> {
							res.setStatus(HttpStatus.OK.value());
							res.setContentType("application/json");
							res.getWriter().write("{\"message\":\"logout successful\"}");
						}).invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID"));

		return http.build();
	}
}
