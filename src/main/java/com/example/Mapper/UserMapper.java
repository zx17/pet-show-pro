package com.example.Mapper;

import com.example.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Xin09 on 2017/2/14.
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findbyname(@Param("name") String name);

    @Insert("insert into user(name,password,con_sign,integral) values(#{name},#{password},#{con_sign},#{integral})")
    int insert(@Param("name")String name,@Param("password") String password,@Param("con_sign") int con_sign,@Param("integral") int integral);

    @Update("update user set integral = #{integral} where id = #{id}")
    int addintegral(@Param("integral")Integer integral,@Param("id")Integer id);

    @Update("update user set con_sign = #{con_sign} where id = #{id}")
    int addcon_sign(@Param("con_sign")Integer con_sign,@Param("id")Integer id);

    @Update("update  user set sign_time = #{sign_time} where id =#{id}")
    int sign_time(@Param("sign_time") String sign_time,@Param("id")Integer id);

    @Select("select integral from user where id =#{id}")
    int findintegral(@Param("id") int id);

    @Select("select sign_time from user where id =#{id}")
    Date findtime(@Param("id") int id);

    @Select("select con_sign from user where id =#{id}")
    int findcon(@Param("id") int id);
}

