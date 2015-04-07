package com.streakapi.crm.datatype;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Snippet [");
		if (userKey != null) {
			builder.append("userKey=");
			builder.append(userKey);
			builder.append(", ");
		}
		if (creationDate != null) {
			builder.append("creationDate=");
			builder.append(creationDate);
			builder.append(", ");
		}
		builder.append("partOfPipeline=");
		builder.append(partOfPipeline);
		builder.append(", ");
		if (snippetText != null) {
			builder.append("snippetText=");
			builder.append(snippetText);
			builder.append(", ");
		}
		if (snippetName != null) {
			builder.append("snippetName=");
			builder.append(snippetName);
			builder.append(", ");
		}
		if (snippetType != null) {
			builder.append("snippetType=");
			builder.append(snippetType);
			builder.append(", ");
		}
		if (key != null) {
			builder.append("key=");
			builder.append(key);
		}
		builder.append("]");
		return builder.toString();
	}
}
