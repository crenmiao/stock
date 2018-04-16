package com.duty.entity;


public class Config extends QueryPage{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2579644443505500075L;
	private String id;
	private String downtime;
	private String senttime;
	private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDowntime() {
		return downtime;
	}
	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}
	public String getSenttime() {
		return senttime;
	}
	public void setSenttime(String senttime) {
		this.senttime = senttime;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilepath() {
		return filepath;
	}

	
}
