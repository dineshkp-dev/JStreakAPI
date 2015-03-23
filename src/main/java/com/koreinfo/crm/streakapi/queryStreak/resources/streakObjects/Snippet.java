package main.com.koreinfo.crm.streakapi.queryStreak.resources.streakObjects;
/**
 * @author dineshkp
 *
 */
public class Snippet {
	private String userKey;
	private String creationDate;
	private boolean partOfPipeline;
	private String snippetText;
	private String snippetName;
	private SnippetType snippetType;
	private String key;
	/**
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}
	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * @return the partOfPipeline
	 */
	public boolean getPartOfPipeline() {
		return partOfPipeline;
	}
	/**
	 * @return the snippetText
	 */
	public String getSnippetText() {
		return snippetText;
	}
	/**
	 * @return the snippetName
	 */
	public String getSnippetName() {
		return snippetName;
	}
	/**
	 * @return the snippetType
	 */
	public SnippetType getSnippetType() {
		return snippetType;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
