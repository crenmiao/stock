package com.duty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.duty.entity.StockRecord;
import com.duty.entity.User;

public interface StockRecordMapper {

	@Insert("INSERT INTO stock_record (id,name,createtime)" +
			"VALUES(#{r.id},#{r.name},#{r.createtime}) ")
	public boolean insertStockRecord(@Param("r")StockRecord r);
	
	@Select("SELECT * FROM stock_record LIMIT #{r.pageSize} OFFSET #{r.offset}")
	public List<StockRecord> getStockRecords(@Param("r")StockRecord r) ;
	
	@Select("SELECT * FROM stock_record where createtime=#{createtime}")
	public List<StockRecord> getStockRecordsByDate(@Param("createtime")String createtime) ;
	
	
	@Select("SELECT group_concat(name) FROM stock_record where createtime=#{createtime}")
	public String getStockStr(@Param("createtime")String createtime) ;
	
	@Insert({
		"<script>",
		"INSERT INTO stock_record (id,name,createtime)",
		"values",
		"<foreach collection='list' item='r' index='index' separator=','>",
		"(#{r.id},#{r.name},#{r.createtime})" ,
		"</foreach>",
		"</script>"
	})
	public boolean insertStockRecords(@Param("list")List<StockRecord> list);
}
