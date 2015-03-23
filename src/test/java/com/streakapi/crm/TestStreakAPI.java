/**
 * 
 */
package test.com.koreinfo.crm.streakapi;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.text.html.parser.Entity;

import main.com.koreinfo.crm.streakapi.queryStreak.StreakAPI;
import main.com.koreinfo.crm.streakapi.queryStreak.StreakAPIImpl;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.InputStreamFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareEverythingForTest;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

import org.powermock.api.mockito.PowerMockito;

import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

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
			assertEquals(streakapi.checkConnection(mockResponse), true);
			
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_CREATED, "TEST PASS"));
			assertEquals(streakapi.checkConnection(mockResponse), true);
			
			//Check for connection exceptions
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "TEST BAD REQUEST"));
			assertEquals(streakapi.checkConnection(mockResponse), false);
			
			when(mockResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_GATEWAY_TIMEOUT, "TEST GATEWAY TIMEOUT"));
			assertEquals(streakapi.checkConnection(mockResponse), false);
			
			// Ensure that the methods of the Mock objects were invoked.
			verify(mockResponse, atLeast(4)).getStatusLine();
			verify(mockEntity, atLeast(4)).getContentLength();
			mockResponse = null;
			assertEquals(streakapi.checkConnection( mockResponse), false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testGetCurrentUser() {
		System.out.println("TestStreakAPI.testGetCurrentUser()");
		try {
			when()
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
