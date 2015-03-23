package main.com.koreinfo.crm.streakapi.exceptions;

/**
 * @author dineshkp
 * NoValidObjectsReturned is thrown when no valid data is returned from a query to the API.
 *
 */
public class NoValidObjectsReturned extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoValidObjectsReturned() {
		super();
	}
	
	public NoValidObjectsReturned(String errMsg) {
		super(errMsg);
	}

}
