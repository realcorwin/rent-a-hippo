package dik.zuulservicegateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.io.IOException;

public class RouteFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		System.out.println("Routing Filter is invoked");
		RequestContext ctx = RequestContext.getCurrentContext();
		Object serviceId = ctx.get("serviceId");
		
		if(serviceId != null && FilterUtils.CURRENCY_CONVERSION_SERVICE_NAME.equals(serviceId.toString())) {
			boolean useNewRoute = FilterUtils.useNewRoute();
			if(useNewRoute) {
				String oldEndPoint = ctx.getRequest().getRequestURI();
				String newEndPoint = "/rent-api/currency-conversion-2";
				
				int index = oldEndPoint.indexOf("cc");
				String strippedRoute = oldEndPoint.substring(index + "cc".length());
				String route = String.format("%s%s", newEndPoint, strippedRoute);
				System.out.println("New route to call----->" + route);
				
				//disable SimpleHostRoutingFilter
				ctx.setRouteHost(null);
				
				//disable RibbonRouting Filter
				ctx.remove(serviceId);
				try {
					RequestContext.getCurrentContext().getResponse().sendRedirect(route);
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return FilterUtils.ROUTE_FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

}
