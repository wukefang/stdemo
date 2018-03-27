package com.st.mapper;

import com.st.model.DemoUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into demo_user (username,password,age) values (#{username},#{password},#{age})")
    int insert(@Param("username") String username,@Param("password") String password,@Param("age") Integer age);

    @Select("select * from demo_user where username=#{username}")
    List<DemoUser> queryUsersByName(@Param("username") String username);
}
