package com.example.sunny.mapper;

import com.example.sunny.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) value (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
