/**
 * 
 */
package com.streakapi.crm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.streakapi.crm.queryStreak.StreakAPIImpl;
import com.streakapi.crm.utils.StreakConnectionUtil;

/**
 * @author dineshkp
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StreakAPIImpl.class)
public class TestStreakAPI {
	StreakAPIImpl streakapi = new StreakAPIImpl("");

	public TestStreakAPI() {
		//		PowerMockito.mock(StreakAPI.class);
	}
	// List of Mocks to be used for Testing.
	CloseableHttpResponse mockResponse = PowerMockito.mock(CloseableHttpResponse.class);
	HttpEntity mockEntity = PowerMockito.mock(HttpEntity.class);
	StreakAPIImpl mockStreakAPI = PowerMockito.mock(StreakAPIImpl.class);

	@Test
	public void testCreateTargetHost() {
		System.out.println("TestStreakAPI.testCreateTargetHost()");

		//		StreakAPI streakapi =  PowerMockito.spy(new StreakAPI(""));// new StreakAPI("");//mock (StreakAPI.class);
		//		StreakAPI streakapi = new StreakAPI("");
		try {
			//			PowerMockito.when(streakapi, "createTargetHost").thenReturn(new HttpHost("www.streak.com",443, "http"));
			assertEquals(streakapi.getTargetHost(), new HttpHost("www.streak.com",443, "https"));
			//Check for connection exceptions
			//			verifyPrivate(streakapi).invoke("createTargetHost");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testCheckConnection() throws IllegalStateException, IOException {
		System.out.println("TestStreakAPI.testCheckConnection()");

		try {
			// Setting up the Mock Response and Entity
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "TEST OK"));
			when(mockEntity.getContent()).thenReturn(new ByteArrayInputStream("TEST VALUE".getBytes()));
			when(mockEntity.getContentLength()).thenReturn(100l);
			when(mockResponse.getEntity()).thenReturn(mockEntity);
			//The Tests
			assertEquals(StreakConnectionUtil.checkHttpResponse(mockResponse), true);
			
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_CREATED, "TEST PASS"));
			assertEquals(StreakConnectionUtil.checkHttpResponse(mockResponse), true);
			
			//Check for connection exceptions
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "TEST BAD REQUEST"));
			assertEquals(StreakConnectionUtil.checkHttpResponse(mockResponse), false);
			
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_GATEWAY_TIMEOUT, "TEST GATEWAY TIMEOUT"));
			assertEquals(StreakConnectionUtil.checkHttpResponse(mockResponse), false);
			
			// Ensure that the methods of the Mock objects were invoked.
			verify(mockResponse, atLeast(4)).getStatusLine();
			verify(mockEntity, atLeast(4)).getContentLength();
			mockResponse = null;
			assertEquals(StreakConnectionUtil.checkHttpResponse( mockResponse), false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testGetCurrentUser() {
		System.out.println("TestStreakAPI.testGetCurrentUser()");
/*		try {
			when()
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
}
