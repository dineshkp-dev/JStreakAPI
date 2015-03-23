package com.streakapi.crm.queryStreak;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;
import com.streakapi.crm.queryStreak.resources.StreakBaseURI;
import com.streakapi.crm.queryStreak.resources.streakObjects.Box;
import com.streakapi.crm.queryStreak.resources.streakObjects.BoxField;
import com.streakapi.crm.queryStreak.resources.streakObjects.Field;
import com.streakapi.crm.queryStreak.resources.streakObjects.Field.TYPE;
import com.streakapi.crm.queryStreak.resources.streakObjects.Pipeline;
import com.streakapi.crm.queryStreak.resources.streakObjects.Stage;
import com.streakapi.crm.queryStreak.resources.streakObjects.Stages;
import com.streakapi.crm.queryStreak.resources.streakObjects.User;

public class StreakAPIImpl {
	private BasicCredentialsProvider credentialsProvider;
	private CloseableHttpClient httpClient;
	private HttpHost targetHost;
	private AuthCache authCache;
	private HttpClientContext context;

	private HttpGet httpGet;
	private HttpPost httpPost;
	private HttpPut httpPut;
	private HttpDelete httpDelete;
	private CloseableHttpResponse response;
	private StreakBaseURI streakBaseURI = new StreakBaseURI();
	private ContentType contentTypeJSON = ContentType.APPLICATION_JSON;
	private ContentType contentTypeURLEncoded = ContentType.APPLICATION_FORM_URLENCODED;

	protected StreakAPIImpl() {	}

	public StreakAPIImpl(String userKey) {
		initStreakConnection(userKey);
	}

	private void initStreakConnection(String userKey) {
		this.createCredentialsProvider(userKey);
		//		this.createHttpClient();
		this.createTargetHost();
		this.createAuthCache();
	}

	protected CloseableHttpClient getHttpClient() {
		return this.httpClient;
	}

	protected HttpClientContext getContext() {
		return this.context;
	}

	public HttpHost getTargetHost() {
		return this.targetHost;
	}

	protected void closeHttpClient() throws IOException {
		System.out.println("Closing HttpClient Connection.");
		this.httpClient.close();
	}

	protected void startHttpClient() {
		this.createHttpClient();
	}

	/**
	 * Set up the Credentials to be used for Authenticating the connection, using
	 * only the 'UserKey' for authentication.
	 * @param userKey
	 */
	private void createCredentialsProvider(String userKey) {
		System.out.println("StreakAPI.createCredentialsProvider()");
		this.credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(
				new AuthScope(AuthScope.ANY_HOST, StreakBaseURI.HTTPSPORTNUMBER),
				new UsernamePasswordCredentials(userKey, ""));
	}

	/**
	 * 
	 */
	private void createHttpClient() {
		System.out.println("StreakAPI.createHttpClient()");
		this.httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
	}

	/**
	 * 
	 */
	private HttpHost createTargetHost() {
		System.out.println("StreakAPI.createTargetHost()");
		return this.targetHost = new HttpHost(StreakBaseURI.HOSTNAME, StreakBaseURI.HTTPSPORTNUMBER, StreakBaseURI.HTTPSCHEME);
	}

	/**
	 * 
	 */
	private void createAuthCache() {
		System.out.println("StreakAPI.createAuthCache()");
		// Create Authentication Cache instance
		authCache = new BasicAuthCache();
		// Generate Basic Authentication scheme and add to the local Authentication cache
		authCache.put(targetHost, new BasicScheme());
		context = HttpClientContext.create();
		context.setCredentialsProvider(credentialsProvider);
		context.setAuthCache(authCache);
	}

	/**
	 * @param response
	 * @return
	 */
	public boolean checkConnection(CloseableHttpResponse response) {

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


/**
 * @return
 * @throws NoValidObjectsReturned
 * @throws IOException 
 */
public User getCurrentUser() throws NoValidObjectsReturned, IOException {
	ObjectMapper mapper = new ObjectMapper();
	User user = null;
	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/users/me").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at User.class:getCurrentUser()");
		}
		user = mapper.readValue(response.getEntity().getContent(), User.class);
	} catch (IllegalStateException | URISyntaxException | IOException e) {
		e.printStackTrace();
	} finally {
			response.close();
			closeHttpClient();
	}
	return user;
}

/**
 * @param userKey
 * @return
 * @throws NoValidObjectsReturned
 */
public User getUser(String userKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getUser()");
	User user = null;
	ObjectMapper mapper = new ObjectMapper();

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/users/"+userKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at User.class:getCurrentUser()");
		}
		user = mapper.readValue(response.getEntity().getContent(), User.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return user;
}

/**
 * @return
 * @throws NoValidObjectsReturned
 */
public List<Pipeline> getAllPipelines() throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getAllPipelines()");
	ObjectMapper mapper = new ObjectMapper();
	List<Pipeline> pipelines = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Pipeline.class);
		pipelines = mapper.readValue(response.getEntity().getContent(), type);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return pipelines;
}

/**
 * @param pipelineKey
 * @return
 * @throws NoValidObjectsReturned
 */
public Pipeline getPipeline(String pipelineKey) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	Pipeline pipeline = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/" + pipelineKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return pipeline;
}

/**
 * @param newPipelineData
 * @return
 * @throws NoValidObjectsReturned
 */
private Pipeline createPipeline(StringBuilder newPipelineData) throws NoValidObjectsReturned {
	Pipeline pipeline = null;
	ObjectMapper mapper = new ObjectMapper();
	StringEntity entity = new StringEntity(newPipelineData.toString(), contentTypeURLEncoded);;

	try {
		startHttpClient();
		httpPut = new HttpPut(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/").build());
		httpPut.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPut, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return pipeline;
}

/**
 * @param pipelineName
 * @param pipelineDescription
 * @return
 * @throws NoValidObjectsReturned
 */
public Pipeline createPipeline(String pipelineName, String pipelineDescription) throws NoValidObjectsReturned {
	StringBuilder pipelineData = new StringBuilder();
	if ((pipelineName != null && pipelineName.length() != 0) && (pipelineDescription != null && pipelineDescription.length() != 0)) {
		pipelineData.append("name=" + pipelineName);
		pipelineData.append("&description=" + pipelineDescription);
	}
	else {
		throw new RuntimeException("New Pipeline name and/or Description cannot be empty/null.");
	}
	return createPipeline(pipelineData);
}

/**
 * @param pipelineName
 * @return
 * @throws NoValidObjectsReturned
 */
public Pipeline createPipeline(String pipelineName) throws NoValidObjectsReturned {
	StringBuilder pipelineData = new StringBuilder();
	if (pipelineName != null && pipelineName.length() != 0) {
		pipelineData.append("name=" + pipelineName);
	}
	else {
		throw new RuntimeException("New Pipeline name cannot be empty/null.");
	}
	return createPipeline(pipelineData);
}

/**
 * @param pipelineKey
 * @return
 * @throws NoValidObjectsReturned
 */
public boolean deletePipeline(String pipelineKey) throws NoValidObjectsReturned {
	Boolean success = false;
	ObjectMapper mapper = new ObjectMapper();
	Map<String,String> mapDeleteResults = null;

	try {
		startHttpClient();
		httpDelete = new HttpDelete(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey).build());

		response = this.getHttpClient().execute(this.getTargetHost(), httpDelete, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.deletePipeline()");
		}
		mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
		success = Boolean.parseBoolean(mapDeleteResults.get("success"));

	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return success;
}

/**
 * @param pipelineKey
 * @param pipeline
 * @return
 * @throws NoValidObjectsReturned
 */
public Pipeline editPipeline(String pipelineKey, Pipeline pipeline) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	HttpEntity entity = null;

	try {
		startHttpClient();
		httpPost = new HttpPost(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/" + pipelineKey).build());
		String jSONString = mapper.writeValueAsString(pipeline);
		entity = new StringEntity(jSONString, contentTypeJSON);
		httpPost.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPost, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.editPipeline()");
		}
		pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return pipeline;
}

/**
 * @return
 * @throws NoValidObjectsReturned
 */
public List<Box> getAllBoxes() throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getBoxes()");
	ObjectMapper mapper = new ObjectMapper();
	List<Box> boxes = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Box.class);
		boxes = mapper.readValue(response.getEntity().getContent(), type);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return boxes;
}

/**
 * @param pipelineKey
 * @return
 * @throws NoValidObjectsReturned
 */
public List<Box> getBoxesInPipeline(String pipelineKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getBoxesInPipeline()");
	ObjectMapper mapper = new ObjectMapper();
	List<Box> boxes = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/boxes").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Box.class);
		boxes = mapper.readValue(response.getEntity().getContent(), type);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return boxes;
}

/**
 * @param boxKey
 * @return
 * @throws NoValidObjectsReturned
 */
public Box getBox(String boxKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getBox()");
	ObjectMapper mapper = new ObjectMapper();
	Box box = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		box = mapper.readValue(response.getEntity().getContent(), Box.class);
		//DEBUG		System.out.println(mapper.writeValueAsString(box));
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return box;
}

/**
 * @param pipelineKey
 * @param newBoxData
 * @return
 * @throws NoValidObjectsReturned
 */
private Box createBox(String pipelineKey, StringBuilder newBoxData) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	StringEntity entity = new StringEntity(newBoxData.toString(), contentTypeURLEncoded);;
	Box box = null;

	try {
		startHttpClient();
		httpPut = new HttpPut(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/boxes").build());
		httpPut.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPut, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		box = mapper.readValue(response.getEntity().getContent(), Box.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return box;
}

/**
 * @param pipelineKey
 * @param boxName
 * @param stageKey
 * @return
 * @throws NoValidObjectsReturned
 */
public Box createBox(String pipelineKey, String boxName, String stageKey) throws NoValidObjectsReturned {
	StringBuilder newBoxData = new StringBuilder();
	if ((boxName != null && boxName.length() != 0) && (stageKey != null && stageKey.length() != 0)) {
		newBoxData.append("name=" + boxName);
		newBoxData.append("&stageKey=" + stageKey);
	}
	else {
		throw new RuntimeException("BoxName and/or StageName cannot be empty/null.");
	}
	return createBox(pipelineKey, newBoxData);
}

/**
 * @param pipelineKey
 * @param boxName
 * @return
 * @throws NoValidObjectsReturned
 */
public Box createBox(String pipelineKey, String boxName) throws NoValidObjectsReturned {
	StringBuilder newBoxData = new StringBuilder();
	if (boxName != null && boxName.length() != 0) {
		newBoxData.append("name=" + boxName);
	}
	else {
		throw new RuntimeException("BoxName and/or StageName cannot be empty/null.");
	}
	return createBox(pipelineKey, newBoxData);
}

/**
 * @param boxKey
 * @return
 * @throws NoValidObjectsReturned
 */
public boolean deleteBox(String boxKey) throws NoValidObjectsReturned {
	Boolean success = false;
	ObjectMapper mapper = new ObjectMapper();
	Map<String,String> mapDeleteResults = null;

	try {
		startHttpClient();
		httpDelete = new HttpDelete(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey).build());

		response = this.getHttpClient().execute(this.getTargetHost(), httpDelete, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
		success = Boolean.parseBoolean(mapDeleteResults.get("success"));

	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return success;
}

/**
 * @param boxKey
 * @param box
 * @return
 * @throws NoValidObjectsReturned
 */
public Box editBox(String boxKey, Box box) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.editBox()");
	ObjectMapper mapper = new ObjectMapper();
	HttpEntity entity = null;

	try {
		startHttpClient();
		httpPost = new HttpPost(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey).build());
		String jSONString = mapper.writeValueAsString(box);
		entity = new StringEntity(jSONString, contentTypeJSON);
		httpPost.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPost, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
		}
		box = mapper.readValue(response.getEntity().getContent(), Box.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return box;
}

public Stages getAllStagesInPipeline(String pipelineKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getAllStagesInPipeline()");
	ObjectMapper mapper = new ObjectMapper();
	Stages stages = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getStagesInPipeline()");
		}
		stages = mapper.readValue(response.getEntity().getContent(), Stages.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return stages;
}

public Stage getStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getAllStagesInPipeline()");
	ObjectMapper mapper = new ObjectMapper();
	Stage stage = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getStagesInPipeline()");
		}
		stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return stage;
}

/**
 * @param pipelineKey
 * @param newStageData
 * @return
 * @throws NoValidObjectsReturned
 */
private Stage createStage(String pipelineKey, StringBuilder newStageData) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	StringEntity entity = new StringEntity(newStageData.toString(), contentTypeURLEncoded);;
	Stage stage = null;

	try {
		startHttpClient();
		httpPut = new HttpPut(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages").build());
		httpPut.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPut, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return stage;
}

/**
 * @param pipelineKey
 * @param stageName
 * @return
 * @throws NoValidObjectsReturned
 */
public Stage createStage(String pipelineKey, String stageName) throws NoValidObjectsReturned {
	StringBuilder newBoxData = new StringBuilder();
	if (stageName != null && stageName.length() != 0) {
		newBoxData.append("name=" + stageName);
	}
	else {
		throw new RuntimeException("StageName cannot be empty/null.");
	}
	return createStage(pipelineKey, newBoxData);
}

/**
 * @param pipelineKey
 * @param stageKey
 * @return
 * @throws NoValidObjectsReturned
 */
public boolean deleteStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned {
	Boolean success = false;
	ObjectMapper mapper = new ObjectMapper();
	Map<String,String> mapDeleteResults = null;

	try {
		startHttpClient();
		httpDelete = new HttpDelete(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build());

		response = this.getHttpClient().execute(this.getTargetHost(), httpDelete, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
		success = Boolean.parseBoolean(mapDeleteResults.get("success"));

	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return success;
}

/**
 * @param pipelineKey
 * @param stageKey
 * @param stage
 * @return
 * @throws NoValidObjectsReturned
 */
public Stage editStage(String pipelineKey,String stageKey, Stage stage) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.editStage()");
	ObjectMapper mapper = new ObjectMapper();
	HttpEntity entity = null;

	try {
		startHttpClient();
		httpPost = new HttpPost(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build());
		String jSONString = mapper.writeValueAsString(stage);
		entity = new StringEntity(jSONString, contentTypeJSON);
		httpPost.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPost, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
		}
		stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return stage;
}

/**
 * @param pipelineKey
 * @return
 * @throws NoValidObjectsReturned
 */
public List<Field> getAllFieldsInPipeline(String pipelineKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getFieldsInPipeline()");
	ObjectMapper mapper = new ObjectMapper();
	List<Field> fields = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/fields").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Field.class);
		fields = mapper.readValue(response.getEntity().getContent(), type);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return fields;
}

/**
 * @param pipelineKey
 * @param fieldKey
 * @return
 * @throws NoValidObjectsReturned
 */
public Field getField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.getAllStagesInPipeline()");
	ObjectMapper mapper = new ObjectMapper();
	Field field = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getStagesInPipeline()");
		}
		field = mapper.readValue(response.getEntity().getContent(), Field.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return field;
}

private Field createField(String pipelineKey, StringBuilder newFieldData) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	StringEntity entity = new StringEntity(newFieldData.toString(), contentTypeURLEncoded);
	Field field = null;

	try {
		startHttpClient();
		httpPut = new HttpPut(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/fields").build());
		httpPut.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPut, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		field = mapper.readValue(response.getEntity().getContent(), Field.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return field;
}

/**
 * @param pipelineKey
 * @param fieldName
 * @param fieldType
 * @return
 * @throws NoValidObjectsReturned
 */
public Field createField(String pipelineKey, String fieldName, TYPE fieldType ) throws NoValidObjectsReturned {
	StringBuilder newFieldData = new StringBuilder();
	if (fieldName != null && fieldName.length() != 0) {
		newFieldData.append("name=" + fieldName);
		newFieldData.append("&type="+fieldType);
	}
	else {
		throw new RuntimeException("FieldName cannot be empty/null.");
	}
	return createField(pipelineKey, newFieldData);
}

/**
 * @param pipelineKey
 * @param fieldKey
 * @return
 * @throws NoValidObjectsReturned
 */
public boolean deleteField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned {
	Boolean success = false;
	ObjectMapper mapper = new ObjectMapper();
	Map<String,String> mapDeleteResults = null;

	try {
		startHttpClient();
		httpDelete = new HttpDelete(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build());

		response = this.getHttpClient().execute(this.getTargetHost(), httpDelete, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
		}
		mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
		success = Boolean.parseBoolean(mapDeleteResults.get("success"));

	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return success;
}

/**
 * @param pipelineKey
 * @param fieldKey
 * @param field
 * @return
 * @throws NoValidObjectsReturned
 */
public Field editField(String pipelineKey, String fieldKey, Field field) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.editBox()");
	ObjectMapper mapper = new ObjectMapper();
	HttpEntity entity = null;

	try {
		startHttpClient();
		httpPost = new HttpPost(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build());
		String jSONString = mapper.writeValueAsString(field);
		entity = new StringEntity(jSONString, contentTypeJSON);
		httpPost.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPost, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
		}
		field = mapper.readValue(response.getEntity().getContent(), Field.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return field;
}

public List<BoxField> getAllFieldsInBox(String boxKey) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	List<BoxField> boxFields = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey+"/fields").build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, BoxField.class);
		boxFields = mapper.readValue(response.getEntity().getContent(), type);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return boxFields;
}

public BoxField getBoxField(String boxKey, String fieldKey) throws NoValidObjectsReturned {
	ObjectMapper mapper = new ObjectMapper();
	BoxField boxField = null;

	try {
		startHttpClient();
		httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey+"/fields/"+fieldKey).build());
		response = this.getHttpClient().execute(this.getTargetHost(), httpGet, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
		}
		System.out.println(mapper.getClass());
		boxField = mapper.readValue(response.getEntity().getContent(), BoxField.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return boxField;
}

public BoxField editFieldValue(String boxKey, String fieldKey, BoxField boxField) throws NoValidObjectsReturned {
	System.out.println("StreakAPI.editBox()");
	ObjectMapper mapper = new ObjectMapper();
	HttpEntity entity = null;

	try {
		startHttpClient();
		httpPost = new HttpPost(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes/"+boxKey+"/fields/"+fieldKey).build());
		String jSONString = mapper.writeValueAsString(boxField);
		entity = new StringEntity(jSONString, contentTypeJSON);
		httpPost.setEntity(entity);

		response = this.getHttpClient().execute(this.getTargetHost(), httpPost, this.getContext());

		if (!checkConnection(response)) {
			throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
		}
		boxField = mapper.readValue(response.getEntity().getContent(), BoxField.class);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	}
	catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
			closeHttpClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return boxField;
}

}
