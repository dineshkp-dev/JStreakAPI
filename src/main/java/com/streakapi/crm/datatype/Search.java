package com.streakapi.crm.datatype;
/**
 * @author dineshkp
 *
 */
public class Search {
	private String resultEntityKey;
	private String title;
	private String snippet;
	private String displayDate;
	private SearchResultType searchResultType;
	private Integer relevanceScore;
	/**
	 * @return the resultEntityKey
	 */
	public String getResultEntityKey() {
		return resultEntityKey;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the snippet
	 */
	public String getSnippet() {
		return snippet;
	}
	/**
	 * @return the displayDate
	 */
	public String getDisplayDate() {
		return displayDate;
	}
	/**
	 * @return the searchResultType
	 */
	public SearchResultType getSearchResultType() {
		return searchResultType;
	}
	/**
	 * @return the relevanceScore
	 */
	public Integer getRelevanceScore() {
		return relevanceScore;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Search [");
		if (resultEntityKey != null) {
			builder.append("resultEntityKey=");
			builder.append(resultEntityKey);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (snippet != null) {
			builder.append("snippet=");
			builder.append(snippet);
			builder.append(", ");
		}
		if (displayDate != null) {
			builder.append("displayDate=");
			builder.append(displayDate);
			builder.append(", ");
		}
		if (searchResultType != null) {
			builder.append("searchResultType=");
			builder.append(searchResultType);
			builder.append(", ");
		}
		if (relevanceScore != null) {
			builder.append("relevanceScore=");
			builder.append(relevanceScore);
		}
		builder.append("]");
		return builder.toString();
	}
}
