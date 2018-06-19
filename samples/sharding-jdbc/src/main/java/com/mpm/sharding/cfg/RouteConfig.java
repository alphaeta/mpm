package com.mpm.sharding.cfg;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "routeconfig")
public class RouteConfig {
	private Map<String, String> comroute;

	public RouteConfig() {
		super();
	}

	public Map<String, String> getComroute() {
		return comroute;
	}

	public void setComroute(Map<String, String> comroute) {
		this.comroute = comroute;
	}
}
