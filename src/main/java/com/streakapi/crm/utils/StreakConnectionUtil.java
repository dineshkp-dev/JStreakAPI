/**
 * 
 */
package com.streakapi.crm.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.streakapi.crm.queryStreak.resources.StreakBaseURI;

/**
 * @author dineshkp
 *
 */
public class StreakConnectionUtil {
	private BasicCredentialsProvider credentialsProvider = null;
	private HttpHost targetHost = null;
	private AuthCache authCache = null;
	private HttpClientContext context = null;



	/**
	 * Creates a HttpClient object.
	 * <li>MUST be called after initializing CredentialsProvider with User-Key.</li>
	 * @return
	 */
	public CloseableHttpClient startHttpClient() {
		if (credentialsProvider == null) {
			throw new RuntimeException("CredentialsProvider has not been initialized with User-Key.");
		}
		return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
	}
	
	public void closeHttpClient(CloseableHttpClient httpClient) throws IOException {
		System.out.println("Closing HttpClient Connection.");
		httpClient.close();
	}
	
	/**
	 * Set up the Credentials to be used for Authenticating the connection, using
	 * only the 'UserKey' for authentication.
	 * @param userKey
	 */
	public BasicCredentialsProvider createCredentialsProvider(String userKey) {
		System.out.println("StreakConnectionUtil.createCredentialsProvider()");
		credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, StreakBaseURI.HTTPSPORTNUMBER),new UsernamePasswordCredentials(userKey, ""));
		return credentialsProvider;
	}
	
	/**
	 * 
	 */
	public HttpHost createTargetHost() {
		System.out.println("StreakConnectionUtil.createTargetHost()");
		return this.targetHost = new HttpHost(StreakBaseURI.HOSTNAME, StreakBaseURI.HTTPSPORTNUMBER, StreakBaseURI.HTTPSCHEME);
	}
	
	/**
	 * @return 
	 * 
	 */
	public AuthCache createAuthCache() {
		System.out.println("StreakConnectionUtil.createAuthCache()");
		// Create Authentication Cache instance
		authCache = new BasicAuthCache();
		// Generate Basic Authentication scheme and add to the local Authentication cache
		authCache.put(targetHost, new BasicScheme());
		context = HttpClientContext.create();
		context.setCredentialsProvider(credentialsProvider);
		context.setAuthCache(authCache);
		return authCache;
	}
	
	public HttpClientContext getHttpClientContext() {
		if (context == null) {
			this.createAuthCache();
		}
		return context;
	}

	/**
	 * Checks for the following conditions:
	 * <li>HttpResponse is not-null</li>
	 * <li>HttpResponse status is less than 300</li>
	 * <li>Checks if the entity value within the HttpResponse is non-null</li>
	 * <br></br>
	 * <p>If the conditions are met, boolean 'True' is returned, else 'False'.
	 * @param response
	 * @return
	 */
	public static boolean checkHttpResponse(CloseableHttpResponse response) {

		Boolean connectionSuccess = true;
		if (response == null) {
			System.out.println("Response cannot be NULL!.");
			return connectionSuccess = false;
		}
		HttpEntity entity = response.getEntity();
		StatusLine statusLine = response.getStatusLine();
		// Ensure there are no Errors in Query 
		if (statusLine.getStatusCode() >= 300) {
			System.out.println("ERROR @ StreakAPI.class:checkConnection : " + statusLine.getStatusCode());
			System.out.println(statusLine.getReasonPhrase());
			connectionSuccess=false;
		}
		//Check if entity is empty
		if (entity == null || (entity.getContentLength() == 0)) {
			System.out.println("ERROR @ StreakAPI.class:checkConnection : Response Entity cannot be NULL or Empty");
			connectionSuccess=false;
		}
		return connectionSuccess;
	}

}
