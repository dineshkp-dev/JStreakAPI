package com.streakapi.crm.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.streakapi.crm.api.IStreakURIBuilderUtil;

public class StreakURIBuilderUtilImpl implements IStreakURIBuilderUtil {
	
	
	private static URI streakBaseURI = new StreakBaseURI().getUri();
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getCurrentUserURI() throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/users/me").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getUserURI(String userKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/users/"+userKey).build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getAllPipelinesURI() throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines").build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getPipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getCreatePipelineURI() throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getDeletePipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey).build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getEditPipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey).build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getAllBoxesURI() throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getBoxesInPipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/boxes").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getBoxURI(String boxKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getCreateBoxURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/boxes").build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getDeleteBoxURI(String boxKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getEditBoxURI(String boxKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getAllStagesInPipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/stages").build();
	}	

	/** 
	 * {@inheritDoc}
	 */
	public URI getStageURI(String pipelineKey, String stageKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getCreateStageURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/stages").build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getDeleteStageURI(String pipelineKey, String stageKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getEditStageURI(String pipelineKey, String stageKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/stages/"+stageKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getAllFieldsInPipelineURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/fields").build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getCreateFieldURI(String pipelineKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/fields").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getDeleteFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build();
	}	
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getEditFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/pipelines/"+pipelineKey+"/fields/"+fieldKey).build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getAllFieldsInBoxURI(String boxKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey+"/fields").build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getBoxFieldURI(String boxKey, String fieldKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey+"/fields/"+fieldKey).build();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public URI getEditFieldValueURI(String boxKey, String fieldKey) throws URISyntaxException {
		return new URIBuilder(streakBaseURI.toString()+"/boxes/"+boxKey+"/fields/"+fieldKey).build();
	}
	
	
	
}
