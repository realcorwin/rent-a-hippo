package dik.zuulservicegateway.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilters {

	@Bean
	public AuthenticationFilter getAuthenticationFilter() {
		return new AuthenticationFilter();
	}

	@Bean
	public ResponseAuditFilter getResponseAuditFilter() {
		return new ResponseAuditFilter();
	}

	@Bean
	public RouteFilter getRouteFilter() {
		return new RouteFilter();
	}

}
