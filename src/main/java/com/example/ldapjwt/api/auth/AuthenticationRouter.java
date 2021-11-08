package com.example.ldapjwt.api.auth;

import com.example.ldapjwt.config.ApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthenticationRouter {

	@Bean
	public RouterFunction<ServerResponse> authRoutes(AuthenticationHandler handler) {
		return RouterFunctions.route(
			RequestPredicates.GET(ApiProperties.AUTH_API_PREFIX + "/login")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
			handler::login);
	}
}
