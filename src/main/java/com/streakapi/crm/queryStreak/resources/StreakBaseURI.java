/**
 * 
 */
package com.streakapi.crm.queryStreak.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

/**
 * @author dineshkp
 *
 */
public class StreakBaseURI {
	
	public static final String HOSTNAME = "www.streak.com";
	public static final Integer HTTPSPORTNUMBER = 443;
	public static final String HTTPSCHEME = "https";
	public static final String STREAKAPIVERSION = "v1";
	public static final String APINAME = "api";
	
	private URI uri;
	
	public StreakBaseURI() {
		this.createBaseURL();
	}
	
	/**
	 * Generates the base Streak URI: https://www.streak.com/api/v1
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 * 
	 */
	private void createBaseURL() {
		try {
			uri = new URIBuilder()
			.setScheme(HTTPSCHEME)
			.setHost(HOSTNAME+"/"+APINAME+"/"+STREAKAPIVERSION)
			.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the base Streak URI
	 * @see com.streakapi.crm.queryStreak.resources.StreakBaseURI#createBaseURL()
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

}
