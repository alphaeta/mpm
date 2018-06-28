package com.mpm.sso;

import java.net.URI;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String BASIC = "Basic";

	public static void main(String[] args) throws Exception {
		String clientId = "sso-client";
		String clientSecret = "mpm";
		String username = "qysm:5007";
		String password = "123456";
		String basic = encodeBasicAuthorizationHeader(clientId, clientSecret);

		CloseableHttpClient httpclient = HttpClients.custom().build();
		HttpUriRequest post = RequestBuilder.post().setUri(new URI("http://localhost:8000/sso/oauth/token"))
				.addParameter("username", username).addParameter("password", password)
				.addParameter("grant_type", "password").addHeader(AUTHORIZATION_HEADER, basic).build();

		CloseableHttpResponse response = httpclient.execute(post);
		try {
			HttpEntity entity = response.getEntity();// 中文乱码
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				String body = EntityUtils.toString(entity, "utf-8");
				System.out.println(body);
			}

		} finally {
			response.close();
		}

	}

	public static String encodeBasicAuthorizationHeader(String clientId, String clientSecret) {
		String str = clientId + ":" + clientSecret;
		return BASIC + " " + Base64.encodeBase64String(str.getBytes());
	}

}
