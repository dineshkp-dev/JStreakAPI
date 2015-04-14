package com.streakapi.crm.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streakapi.crm.api.IStreakAPI;
import com.streakapi.crm.api.IStreakURIBuilderUtil;
import com.streakapi.crm.datatype.Box;
import com.streakapi.crm.datatype.BoxField;
import com.streakapi.crm.datatype.Field;
import com.streakapi.crm.datatype.Pipeline;
import com.streakapi.crm.datatype.Reminder;
import com.streakapi.crm.datatype.Stage;
import com.streakapi.crm.datatype.Stages;
import com.streakapi.crm.datatype.User;
import com.streakapi.crm.datatype.Field.TYPE;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;
import com.streakapi.crm.utils.StreakBaseURI;
import com.streakapi.crm.utils.StreakConnectionUtil;
import com.streakapi.crm.utils.StreakURIBuilderUtilImpl;

/**
 * @author dineshkp
 *
 */
public class StreakAPIImpl implements IStreakAPI{
	@SuppressWarnings("unused")
	private BasicCredentialsProvider credentialsProvider;
	private CloseableHttpClient httpClient;
	private HttpHost targetHost;
	@SuppressWarnings("unused")
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
	private IStreakURIBuilderUtil streakURI = new StreakURIBuilderUtilImpl();

	private StreakConnectionUtil streakConnUtil = null;

	StreakAPIImpl() {	}

	public StreakAPIImpl(String userKey) {
		initStreakConnection(userKey);
	}

	private void initStreakConnection(String userKey) {
		streakConnUtil = new StreakConnectionUtil();
		this.credentialsProvider = streakConnUtil.createCredentialsProvider(userKey);
		this.targetHost = streakConnUtil.createTargetHost();
		this.authCache = streakConnUtil.createAuthCache();
		this.context = streakConnUtil.getHttpClientContext();
	}

	public StreakConnectionUtil getStreakConnectionUtil() {
		return this.streakConnUtil;
	}

	private HttpClientContext getContext() {
		return this.context;
	}

	private HttpHost getTargetHost() {
		return this.targetHost;
	}


	/**
	 * {@inheritDoc}
	 */
	public User getCurrentUser() throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getCurrentUserURI());
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());
			System.err.println("Got response...");
			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at User.class:getCurrentUser()");
			}
			user = mapper.readValue(response.getEntity().getContent(), User.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public User getUser(String userKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getUser()");
		User user = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getUserURI(userKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at User.class:getCurrentUser()");
			}
			user = mapper.readValue(response.getEntity().getContent(), User.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Pipeline> getAllPipelines() throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getAllPipelines()");
		ObjectMapper mapper = new ObjectMapper();
		List<Pipeline> pipelines = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getAllPipelinesURI());
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Pipeline.class);
			pipelines = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pipelines;
	}

	/**
	 * {@inheritDoc}
	 */
	public Pipeline getPipeline(String pipelineKey) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		Pipeline pipeline = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getPipelineURI(pipelineKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pipeline;
	}

	/**
	 * {@inheritDoc}
	 */
	private Pipeline createPipeline(StringBuilder newPipelineData) throws NoValidObjectsReturned {
		Pipeline pipeline = null;
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(newPipelineData.toString(), contentTypeURLEncoded);;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPut = new HttpPut(streakURI.getCreatePipelineURI());
			httpPut.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPut, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pipeline;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public boolean deletePipeline(String pipelineKey) throws NoValidObjectsReturned {
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> mapDeleteResults = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpDelete = new HttpDelete(streakURI.getDeletePipelineURI(pipelineKey));

			response = httpClient.execute(this.getTargetHost(), httpDelete, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.deletePipeline()");
			}
			mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
			success = Boolean.parseBoolean(mapDeleteResults.get("success"));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	public Pipeline editPipeline(String pipelineKey, Pipeline pipeline) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditPipelineURI(pipelineKey));
			String jSONString = mapper.writeValueAsString(pipeline);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.editPipeline()");
			}
			pipeline = mapper.readValue(response.getEntity().getContent(), Pipeline.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pipeline;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Box> getAllBoxes() throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getBoxes()");
		ObjectMapper mapper = new ObjectMapper();
		List<Box> boxes = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/boxes").build());
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Box.class);
			boxes = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boxes;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Box> getBoxesInPipeline(String pipelineKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getBoxesInPipeline()");
		ObjectMapper mapper = new ObjectMapper();
		List<Box> boxes = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getBoxesInPipelineURI(pipelineKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Box.class);
			boxes = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boxes;
	}

	/**
	 * {@inheritDoc}
	 */
	public Box getBox(String boxKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getBox()");
		ObjectMapper mapper = new ObjectMapper();
		Box box = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getBoxURI(boxKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			box = mapper.readValue(response.getEntity().getContent(), Box.class);
			//DEBUG		System.out.println(mapper.writeValueAsString(box));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return box;
	}

	/**
	 * {@inheritDoc}
	 */
	private Box createBox(String pipelineKey, StringBuilder newBoxData) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(newBoxData.toString(), contentTypeURLEncoded);;
		Box box = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPut = new HttpPut(streakURI.getCreateBoxURI(pipelineKey));
			httpPut.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPut, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			box = mapper.readValue(response.getEntity().getContent(), Box.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return box;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public boolean deleteBox(String boxKey) throws NoValidObjectsReturned {
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> mapDeleteResults = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpDelete = new HttpDelete(streakURI.getDeleteBoxURI(boxKey));

			response = httpClient.execute(this.getTargetHost(), httpDelete, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
			success = Boolean.parseBoolean(mapDeleteResults.get("success"));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	public Box editBox(String boxKey, Box box) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.editBox()");
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditBoxURI(boxKey));
			String jSONString = mapper.writeValueAsString(box);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
			}
			box = mapper.readValue(response.getEntity().getContent(), Box.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return box;
	}

	/**
	 * {@inheritDoc}
	 */
	public Stages getAllStagesInPipeline(String pipelineKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getAllStagesInPipeline()");
		ObjectMapper mapper = new ObjectMapper();
		Stages stages = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getAllStagesInPipelineURI(pipelineKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getAllStagesInPipeline()");
			}
			stages = mapper.readValue(response.getEntity().getContent(), Stages.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Caught Illegal-State/URISyntax/IO Exception at StreakAPI.getAllStagesInPipeline()");
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("IO Exception at StreakAPI.getAllStagesInPipeline()");
			}
		}
		return stages;
	}

	/**
	 * {@inheritDoc}
	 */
	public Stage getStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getAllStagesInPipeline()");
		ObjectMapper mapper = new ObjectMapper();
		Stage stage = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(new URIBuilder(streakBaseURI.getUri().toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build());
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getStagesInPipeline()");
			}
			stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stage;
	}

	/**
	 * Creates a Stage Object in Streak using Stage Data passed as 'StringBuilder' and the pipelineKey.
	 * @param pipelineKey
	 * @param newStageData 
	 * @return Stage
	 * @throws NoValidObjectsReturned
	 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api</a>
	 */
	private Stage createStage(String pipelineKey, StringBuilder newStageData) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(newStageData.toString(), contentTypeURLEncoded);;
		Stage stage = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPut = new HttpPut(streakURI.getCreateStageURI(pipelineKey));
			httpPut.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPut, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stage;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public boolean deleteStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned {
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> mapDeleteResults = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpDelete = new HttpDelete(streakURI.getDeleteStageURI(pipelineKey, stageKey));

			response = httpClient.execute(this.getTargetHost(), httpDelete, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
			success = Boolean.parseBoolean(mapDeleteResults.get("success"));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	public Stage editStage(String pipelineKey,String stageKey, Stage stage) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.editStage()");
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditStageURI(pipelineKey, stageKey));
			String jSONString = mapper.writeValueAsString(stage);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
			}
			stage = mapper.readValue(response.getEntity().getContent(), Stage.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stage;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Field> getAllFieldsInPipeline(String pipelineKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getFieldsInPipeline()");
		ObjectMapper mapper = new ObjectMapper();
		List<Field> fields = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getAllFieldsInPipelineURI(pipelineKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Field.class);
			fields = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fields;
	}

	/**
	 * {@inheritDoc}
	 */
	public Field getField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.getAllStagesInPipeline()");
		ObjectMapper mapper = new ObjectMapper();
		Field field = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getFieldURI(pipelineKey, fieldKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.getStagesInPipeline()");
			}
			field = mapper.readValue(response.getEntity().getContent(), Field.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return field;
	}

	/**
	 * Creates a Field Object in Streak using Field Data passed as 'StringBuilder' and the pipelineKey.
	 * @param pipelineKey
	 * @param newFieldData
	 * @return {@link Field}
	 * @throws NoValidObjectsReturned
	 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api</a>
	 */
	private Field createField(String pipelineKey, StringBuilder newFieldData) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(newFieldData.toString(), contentTypeURLEncoded);
		Field field = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPut = new HttpPut(streakURI.getCreateFieldURI(pipelineKey));
			httpPut.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPut, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			field = mapper.readValue(response.getEntity().getContent(), Field.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return field;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public boolean deleteField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned {
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> mapDeleteResults = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpDelete = new HttpDelete(streakURI.getDeleteFieldURI(pipelineKey, fieldKey));

			response = httpClient.execute(this.getTargetHost(), httpDelete, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
			success = Boolean.parseBoolean(mapDeleteResults.get("success"));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	public Field editField(String pipelineKey, String fieldKey, Field field) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.editBox()");
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditFieldURI(pipelineKey, fieldKey));
			String jSONString = mapper.writeValueAsString(field);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
			}
			field = mapper.readValue(response.getEntity().getContent(), Field.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return field;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BoxField> getAllFieldsInBox(String boxKey) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		List<BoxField> boxFields = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getAllFieldsInBoxURI(boxKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, BoxField.class);
			boxFields = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boxFields;
	}

	/**
	 * {@inheritDoc}
	 */
	public BoxField getBoxField(String boxKey, String fieldKey) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		BoxField boxField = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getBoxFieldURI(boxKey, fieldKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			boxField = mapper.readValue(response.getEntity().getContent(), BoxField.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boxField;
	}

	/**
	 * {@inheritDoc}
	 */
	public BoxField editFieldValue(String boxKey, String fieldKey, BoxField boxField) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.editBox()");
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditFieldValueURI(boxKey, fieldKey));
			String jSONString = mapper.writeValueAsString(boxField);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
			}
			boxField = mapper.readValue(response.getEntity().getContent(), BoxField.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boxField;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Reminder> getAllRemindersForBox(String boxKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPIImpl.getAllRemindersForBox()");
		ObjectMapper mapper = new ObjectMapper();
		List<Reminder> reminders = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getAllRemindersForBoxURI(boxKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Reminder.class);
			reminders = mapper.readValue(response.getEntity().getContent(), type);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reminders;
	}

	/**
	 * {@inheritDoc}
	 */
	public Reminder getReminder(String reminderKey) throws NoValidObjectsReturned {
		System.out.println("StreakAPIImpl.getReminder()");
		ObjectMapper mapper = new ObjectMapper();
		Reminder reminder = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpGet = new HttpGet(streakURI.getReminderURI(reminderKey));
			response = httpClient.execute(this.getTargetHost(), httpGet, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at Pipeline.class:getAllPipelines()");
			}
			System.out.println(mapper.getClass());
			reminder = mapper.readValue(response.getEntity().getContent(), Reminder.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reminder;
	}

	/**
	 * Creates a Reminder Object in Streak using Reminder Data passed as 'StringBuilder' and the pipelineKey.
	 * @param pipelineKey
	 * @param newFieldData
	 * @return {@link Reminder}
	 * @throws NoValidObjectsReturned
	 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api</a>
	 */
	private Reminder createReminder(String boxKey, StringBuilder newReminderData) throws NoValidObjectsReturned {
		ObjectMapper mapper = new ObjectMapper();
		StringEntity entity = new StringEntity(newReminderData.toString(), contentTypeURLEncoded);
		Reminder reminder = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPut = new HttpPut(streakURI.getCreateFieldURI(boxKey));
			httpPut.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPut, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			reminder = mapper.readValue(response.getEntity().getContent(), Reminder.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reminder;
	}

	/**
	 * {@inheritDoc}
	 */
	public Reminder createReminder(String boxKey, String message, Long remindDate, boolean remindFollowers ) throws NoValidObjectsReturned {
		StringBuilder newReminderData = new StringBuilder();
		if (message != null && message.length() != 0){
			throw new RuntimeException("Message field for Reminder cannot be empty/null.");
		}
		else if (remindDate != null && remindDate.intValue() != 0) {
			throw new RuntimeException("Remind Date field for Reminder cannot be empty/null.");
		}

		newReminderData.append("remindDate=" + remindDate);
		newReminderData.append("&remindFollowers="+ remindFollowers);
		newReminderData.append("&message="+message);
		return createReminder(boxKey, newReminderData);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteReminder(String reminderKey) throws NoValidObjectsReturned {
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> mapDeleteResults = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpDelete = new HttpDelete(streakURI.getDeleteReminderURI(reminderKey));

			response = httpClient.execute(this.getTargetHost(), httpDelete, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.createBox()");
			}
			mapDeleteResults = mapper.readValue(response.getEntity().getContent(), new TypeReference<HashMap<String, String>>(){});
			success = Boolean.parseBoolean(mapDeleteResults.get("success"));
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	public Reminder editReminder(String reminderKey, Reminder reminder) throws NoValidObjectsReturned {
		System.out.println("StreakAPI.editBox()");
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity entity = null;

		try {
			httpClient = streakConnUtil.startHttpClient();
			httpPost = new HttpPost(streakURI.getEditReminderURI(reminderKey));
			String jSONString = mapper.writeValueAsString(reminder);
			entity = new StringEntity(jSONString, contentTypeJSON);
			httpPost.setEntity(entity);

			response = httpClient.execute(this.getTargetHost(), httpPost, this.getContext());

			if (!StreakConnectionUtil.checkHttpResponse(response)) {
				throw new NoValidObjectsReturned("No valid data for Streak Query at StreakAPI.class:editBox()");
			}
			reminder = mapper.readValue(response.getEntity().getContent(), Reminder.class);
		} catch (IllegalStateException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				response.close();
				streakConnUtil.closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reminder;
	}


}
