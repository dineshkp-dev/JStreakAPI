/**
 * 
 */
package com.streakapi.crm.api;

import java.io.IOException;
import java.util.List;

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

/**
 * @author dineshkp
 *
 */
public interface IStreakAPI {

	/**
	 * Gets information about the current User from Streak API.
	 * @return User
	 * @see User
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public User getCurrentUser() throws NoValidObjectsReturned;

	/**
	 * Gets information about a specific User from Streak API, identified through their User-Key.
	 * @return User
	 * @see User
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public User getUser(String userKey) throws NoValidObjectsReturned; 

	/**
	 * Gets a &lt;List> of all the Pipelines available for the User.
	 * @return List&lt;Pipeline>
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 * @throws IOException
	 */
	public List<Pipeline> getAllPipelines() throws NoValidObjectsReturned;

	/**
	 * Gets a specific Pipeline within the user's account.
	 * @param pipelineKey
	 * @return Pipeline
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline getPipeline(String pipelineKey) throws NoValidObjectsReturned;
	
	/**
	 * Creates a Pipeline and returns a Pipeline Object if successful.
	 * @param pipelineName
	 * @param pipelineDescription
	 * @return Pipeline
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline createPipeline(String pipelineName, String pipelineDescription) throws NoValidObjectsReturned;
	
	/**
	 * Creates a Pipeline, with only the Pipeline Name parameter, and returns a Pipeline Object if successful.
	 * @param pipelineName
	 * @return Pipeline
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline createPipeline(String pipelineName) throws NoValidObjectsReturned;
	
	/**
	 * Deletes a Pipeline and returns 'true' if successful.
	 * @param pipelineKey
	 * @return boolean
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean deletePipeline(String pipelineKey) throws NoValidObjectsReturned;

	/**
	 * Edit a Pipeline and returns the entire Pipeline after edit.
	 * @param pipelineKey
	 * @param pipeline
	 * @return boolean
	 * @see Pipeline
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline editPipeline(String pipelineKey, Pipeline pipeline) throws NoValidObjectsReturned;
	
	/**
	 * Gets a &lt;List> of all the Boxes available for the User.
	 * @return List&lt;Box>
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Box> getAllBoxes() throws NoValidObjectsReturned;
	
	/**
	 * Gets a &lt;List> of all the Boxes available for a specific Pipeline.
	 * @param pipelineKey
	 * @return List&lt;Box>
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Box> getBoxesInPipeline(String pipelineKey) throws NoValidObjectsReturned;
	
	/**
	 * Gets a specific Box based on the Box-Key
	 * @param boxKey
	 * @return Box
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box getBox(String boxKey) throws NoValidObjectsReturned;
	
	/**
	 * Creates a Box in Streak with a specified Box Name and a Stage Key information.
	 * @param pipelineKey
	 * @param boxName
	 * @param stageKey
	 * @return Box
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box createBox(String pipelineKey, String boxName, String stageKey) throws NoValidObjectsReturned;
	
	/**
	 * Creates a Box in Streak with only a Box Name and the PipelineKey.
	 * @param pipelineKey
	 * @param boxName
	 * @return Box
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box createBox(String pipelineKey, String boxName) throws NoValidObjectsReturned;
	
	/**
	 * Deletes a Box and returns 'true' if successful.
	 * @param boxKey
	 * @return boolean
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean deleteBox(String boxKey) throws NoValidObjectsReturned;
	
	/**
	 * Edits the information in a Box and returns the edited Box object.
	 * @param boxKey
	 * @param Box
	 * @return boolean
	 * @see Box
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box editBox(String boxKey, Box box) throws NoValidObjectsReturned;
	
	/**
	 * Get all the Stages in a Pipeline.
	 * @param pipelineKey
	 * @return Stages
	 * @see Stages
	 * @see Stage
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Stages getAllStagesInPipeline(String pipelineKey) throws NoValidObjectsReturned;
	
	/**
	 * Get a specific Stage from a Pipeline.
	 * @param pipelineKey
	 * @param stageKey
	 * @return Stage
	 * @see Stage
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Stage getStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned;
	
	/**
	 * Creates a new Stage and returns the new Stage values.
	 * @param pipelineKey
	 * @param stageName
	 * @return Stage
	 * @see Stage
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Stage createStage(String pipelineKey, String stageName) throws NoValidObjectsReturned;
	
	/**
	 * Deletes an existing Stage and returns 'true' if successful.
	 * @param pipelineKey
	 * @param stageName
	 * @return boolean
	 * @see Stage
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean deleteStage(String pipelineKey, String stageKey) throws NoValidObjectsReturned;
	
	/**
	 * Edits an existing Stage and returns the Stage after editing.
	 * @param stageKey
	 * @param stage
	 * @return Stage
	 * @see Stage
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Stage editStage(String pipelineKey,String stageKey, Stage stage) throws NoValidObjectsReturned;
	
	/**
	 * Gets a &lt;List> of all the Fields available in a Pipeline.
	 * @param pipelineKey
	 * @return Field
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Field> getAllFieldsInPipeline(String pipelineKey) throws NoValidObjectsReturned;
	
	/**
	 * Gets a specific Field available in a Pipeline.
	 * @param pipelineKey
	 * @param fieldKey
	 * @return Field
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field getField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned;
	
	/**
	 * Creates a new Field in a Pipeline, with the Field Name and Field Type.
	 * @param pipelineKey
	 * @param fieldName
	 * @param fieldType
	 * @return Field
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field createField(String pipelineKey, String fieldName, TYPE fieldType ) throws NoValidObjectsReturned;
	
	/**
	 * Deletes an existing Field in a Pipeline and returns 'true' if successful.
	 * @param pipelineKey
	 * @param fieldKey
	 * @return boolean
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean deleteField(String pipelineKey, String fieldKey) throws NoValidObjectsReturned;
	
	/**
	 * Edit an existing Field in a Pipeline and returns the Field after editing.
	 * @param pipelineKey
	 * @param fieldKey
	 * @param field
	 * @return Field
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field editField(String pipelineKey, String fieldKey, Field field) throws NoValidObjectsReturned;
	
	/**
	 * Get a &lt;List> of all the Box's-Fields in a Box.
	 * @param boxKey
	 * @return &lt;List>BoxField
	 * @see BoxField
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<BoxField> getAllFieldsInBox(String boxKey) throws NoValidObjectsReturned;
	
	/**
	 * Get a specific Box's Field Object.
	 * @param boxKey
	 * @param fieldKey
	 * @return BoxField
	 * @see BoxField
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public BoxField getBoxField(String boxKey, String fieldKey) throws NoValidObjectsReturned;
	
	/**
	 * Edit an Existing Box's Field Value.
	 * @param boxKey
	 * @param fieldKey
	 * @return BoxField
	 * @see BoxField
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public BoxField editFieldValue(String boxKey, String fieldKey, BoxField boxField) throws NoValidObjectsReturned;
	
	/**
	 * Gets a &lt;List> of all the Reminders available in a specific Box.
	 * @param boxKey
	 * @return List&lt;Reminder>
	 * @see Reminder
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Reminder> getAllRemindersForBox(String boxKey) throws NoValidObjectsReturned;
	
	/**
	 * Gets a specific Reminder from any of the Pipelines.
	 * @param reminderKey
	 * @return Reminder
	 * @see Reminder
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Reminder getReminder(String reminderKey) throws NoValidObjectsReturned;
	
	/**
	 * Gets a specific Reminder from any of the Pipelines.
	 * @param reminderKey
	 * @return Reminder
	 * @see Reminder
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Reminder createReminder(String boxKey, String message, Long reminDate, boolean remindFollowers) throws NoValidObjectsReturned;
	
	/**
	 * Deletes an existing Reminder in a Pipeline and returns 'true' if successful.
	 * @param reminderKey
	 * @return boolean
	 * @see Field
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean deleteReminder(String reminderKey) throws NoValidObjectsReturned;
	
	/**
	 * Edit an Existing Reminder's Value.
	 * @param reminderKey
	 * @param reminder
	 * @return Reminder
	 * @see Reminder
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Reminder editReminder(String reminderKey, Reminder reminder) throws NoValidObjectsReturned;
	
}
