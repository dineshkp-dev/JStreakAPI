package com.streakapi.crm.queryStreak.resources.streakObjects;
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
}
