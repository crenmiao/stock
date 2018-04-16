package com.duty.entity;

import java.util.Date;

public class StockRecord extends QueryPage{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8337397485954738020L;
	private String id;
	private String name;
	
	private String createtime;//创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return createtime;
	}

	 
	
	
	
}
