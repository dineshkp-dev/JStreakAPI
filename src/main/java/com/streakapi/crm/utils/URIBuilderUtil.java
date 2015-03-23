package com.streakapi.crm.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.streakapi.crm.queryStreak.resources.StreakBaseURI;

public class URIBuilderUtil {
	private static URI streakBaseURI = new StreakBaseURI().getUri();
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/users/me</i>
	 * @return
	 * @throws URISyntaxException
	 */
	public URI getCurrentUserURI() throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/users/me").build();
	}
	
	
}
