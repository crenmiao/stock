package com.duty.entity;



public class SendRecord extends QueryPage{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2344008043606306221L;
	private String id;
	private String issend;
	private String status;
	private String senddate;//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssend() {
		return issend;
	}
	public void setIssend(String issend) {
		this.issend = issend;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	
	
}
