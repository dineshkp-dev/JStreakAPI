package com.streakapi.crm.datatype;

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("File [");
		if (fileOwner != null) {
			builder.append("fileOwner=");
			builder.append(fileOwner);
			builder.append(", ");
		}
		if (size != null) {
			builder.append("size=");
			builder.append(size);
			builder.append(", ");
		}
		if (mimeType != null) {
			builder.append("mimeType=");
			builder.append(mimeType);
			builder.append(", ");
		}
		if (fileName != null) {
			builder.append("fileName=");
			builder.append(fileName);
			builder.append(", ");
		}
		if (mainFileName != null) {
			builder.append("mainFileName=");
			builder.append(mainFileName);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
