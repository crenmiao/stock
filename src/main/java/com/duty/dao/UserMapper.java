package com.duty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.duty.entity.User;

public interface UserMapper {

	@Insert("INSERT INTO alluser (id,NAME,email,pwd,subscription,registtime,subtime,type)" +
			"VALUES(#{u.id},#{u.name},#{u.email},#{u.pwd},#{u.subscription},#{u.registtime},#{u.subtime},#{u.type}) ")
	public boolean insertUser(@Param("u")User u);
	
	@Select("SELECT * FROM alluser where type=#{u.type} order by registtime desc LIMIT #{u.pageSize} OFFSET #{u.offset}")
	public List<User> getUsers(@Param("u")User u) ;
	
	@Select("SELECT count(1) FROM alluser where type=#{u.type}")
	public int countUser(@Param("u")User u) ;
	
	@Select("SELECT * FROM alluser where email=#{email}")
	public User getUser(@Param("email")String email) ;
	
	@Select("SELECT * FROM alluser where email = #{email} and pwd = #{pwd}")
	public User login(@Param("email")String email ,@Param("pwd")String pwd) ;
	
	@Update("update alluser set subscription=1,SUBTIME= NOW() where id=#{id}")
	public boolean subscript(@Param("id")String id) ;
	
	@Update("update alluser set subscription=0 where id=#{id}")
	public boolean unsubscript(@Param("id")String id) ;
	
	@Delete("delete from alluser where id=#{id}")
	public boolean delUser(@Param("id") String id);
	
	
	@Select("SELECT * FROM alluser where subscription=#{subscription}")
	public List<User> getUserBySubscipt(@Param("subscription")String subscription) ;
}
