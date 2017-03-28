package com.search.Descriptor;

import java.util.HashMap;

/**
 * 
 * @author Rizwan Khan
 * @Remark This class will be used for maping the search results from the Logs
 *
 */
public class FinalOutput {

	public static String STATUS_SUCCESS="sucess";
	public static String STATUS_ERROR="error";
	
	private int totalCount;
	
	private String status;
	
	private String exception;	
	
	private HashMap<String,Integer> logWiseMap;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public HashMap<String, Integer> getLogWiseMap() {
		return logWiseMap;
	}
	public void setLogWiseMap(HashMap<String, Integer> logWiseMap) {
		this.logWiseMap = logWiseMap;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	
}
