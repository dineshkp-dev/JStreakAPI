/**
 * 
 */
package com.streakapi.crm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author dineshkp
 *
 */
public class StreakConnectionUtil {

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
