package com.duty.entity;

import java.io.Serializable;
/**
 * 公共分页信息
 * @author 
 * @since  2016-3-15
 *
 */

public class QueryPage implements Serializable{
	private static final long serialVersionUID = -1546301775635205510L;
	

	public final int DEFAULT_PAGE_SIZE = 5;
	public final int DEFAULT_PAGE_NO =1;
	private boolean setPage = false;
	
	private int count;
	protected int pageNo;	   //当前页数    
	protected int pageSize;	   //查询的条数
	private int offset;		//开始查询的记录数
	protected String sortColumns;	//排序的整个sql 语句语句   列：order by time asc  或者 order by time desc
	
	public QueryPage() {
		pageSize = this.DEFAULT_PAGE_SIZE;
		pageNo = this.DEFAULT_PAGE_NO;
		offset = this.DEFAULT_PAGE_SIZE*this.DEFAULT_PAGE_NO;
	}
	
	/**
	 * 
	 * @param pageNo     当前页数
	 * @param pageSize   页面显示条数,默认10条
	 */
    public QueryPage(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.setPage = true;
        this.offset = (pageNo-1)*pageSize;
        
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.setPage = true;
		getOffset();
	}
	/**
	 * 排序字符串
	 * @return
	 */
	public String getSortColumns() {
		return sortColumns;
	}
	/**
	 * 设置排序字符串：eg( a desc, b asc )
	 * @param sortColumns
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}
	public int getOffset() {
		int page = this.pageNo == 0 ? 1 : this.pageNo;
		offset = (page - 1 )* (this.setPage ? this.pageSize : this.DEFAULT_PAGE_SIZE);
		return offset;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
