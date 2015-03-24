package com.streakapi.crm.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Generates the URI for various Streak queries. All values returned are full URIs of 
 * the form <i>https://www.streak.com/api/v1/{queryDependent}</i>
 * @author dineshkp
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api</a>

 *
 */
public interface StreakURIBuilderUtil {
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/users/me</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
	 */
	URI getCurrentUserURI() throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/users/{userkey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getUserURI(String userKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getAllPipelinesURI() throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getPipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getCreatePipelineURI() throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getDeletePipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getEditPipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getAllBoxesURI() throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/boxes"</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getBoxesInPipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}"</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getBoxURI(String boxKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/boxes</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getCreateBoxURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getDeleteBoxURI(String boxKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getEditBoxURI(String boxKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/stages</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getAllStagesInPipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/stages/{stageKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getStageURI(String pipelineKey, String stageKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/stages</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getCreateStageURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/stages/{stageKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getDeleteStageURI(String pipelineKey, String stageKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/stages/{stageKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getEditStageURI(String pipelineKey, String stageKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getAllFieldsInPipelineURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields/{fieldsKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getCreateFieldURI(String pipelineKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields/{fieldsKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getDeleteFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields/{fieldsKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getEditFieldURI(String pipelineKey, String fieldKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}/fields</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getAllFieldsInBoxURI(String boxKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}/fields/{fieldKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getBoxFieldURI(String boxKey, String fieldKey) throws URISyntaxException;
	
	/**
	 * Returns the URI: <i>https://www.streak.com/api/v1/boxes/{boxKey}/fields/{fieldKey}</i>
	 * @return
	 * @throws URISyntaxException
	 * @see <a href="https://www.streak.com/api">https://www.streak.com/api/v1</a>
	 */
	URI getEditFieldValueURI(String boxKey, String fieldKey) throws URISyntaxException;
	
}
