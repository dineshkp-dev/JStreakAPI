package com.streakapi.crm.queryStreak.resources.streakObjects;

/**
 * @author dineshkp
 *
 */
public class File {
	private String fileOwner;
	private Integer size;
	private String mimeType;
	private String fileName;
	private String mainFileName;
	/**
	 * @return the fileOwner
	 */
	public String getFileOwner() {
		return fileOwner;
	}
	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @return the mainFileName
	 */
	public String getMainFileName() {
		return mainFileName;
	}

}
