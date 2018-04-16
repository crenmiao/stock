package com.duty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.duty.entity.Config;
import com.duty.entity.User;

public interface ConfigMapper {

	@Insert("INSERT INTO config (id,downtime,senttime,filepath)" +
			"VALUES(#{u.id},#{u.downtime},#{u.senttime},#{u.filepath}) ")
	public boolean insertConfig(@Param("u")Config u);
	
	@Select("SELECT * FROM config ")
	public List<Config> getConfig() ;
	
	@Update("update config set downtime=#{u.downtime},senttime=#{u.senttime}" +
			",filepath=#{u.filepath} where id=#{u.id}")
	public boolean updateConfig(@Param("u")Config u);
}
