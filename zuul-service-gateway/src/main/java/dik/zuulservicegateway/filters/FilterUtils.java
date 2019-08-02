package dik.zuulservicegateway.filters;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.Random;

public class FilterUtils {

	public static final String RENT_SERVICE_AUTH_TOKEN_KEY = "token";
	public static final String RENT_SERVICE_AUTH_TOKEN_VALUE = "secret";

	public static final String RENT_SERVICE_SERVICE_NAME = "rent-service";
	public static final String CURRENCY_CONVERSION_SERVICE_NAME = "currency-conversion";

	public static final int AUTHENTICATION_FILTER_ORDER = FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
	public static final int ROUTE_FILTER_ORDER = AUTHENTICATION_FILTER_ORDER + 1;
	public static final int RESPONSE_AUDIT_FILTER_ORDER = ROUTE_FILTER_ORDER + 1;
	
	
	public static boolean useNewRoute() {
		Random random = new Random();
		int value = random.nextInt(10);
		System.out.println(value);
//		return value < 3;
		return false;
	}

}
