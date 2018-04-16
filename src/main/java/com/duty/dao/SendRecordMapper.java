package com.duty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.duty.entity.SendRecord;
import com.duty.entity.StockRecord;
import com.duty.entity.User;

public interface SendRecordMapper {

	@Insert("INSERT INTO send_record (id,issend,senddate,status)" +
			"VALUES(#{r.id},#{r.issend},#{r.senddate},#{r.status}) ")
	public boolean insert(@Param("r")SendRecord r);
	
	@Select("SELECT * FROM send_record LIMIT #{r.pageSize} OFFSET #{r.offset}")
	public List<SendRecord> getSendRecords(@Param("r")SendRecord r) ;
	
	@Select("SELECT * FROM send_record where senddate=#{senddate}")
	public SendRecord getSendRecordByDate(@Param("senddate")String senddate) ;
	
	
	@Update("update send_record set issend='1' where senddate=#{senddate}")
	public boolean updateStatus(@Param("senddate")String senddate) ;
}
