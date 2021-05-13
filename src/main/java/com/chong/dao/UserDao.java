package com.chong.dao;

import com.chong.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao接口
 */
@Repository
public interface UserDao {

    // 保存用户信息
    @Insert("insert into user (name,password) values (#{name},#{password})")
    public void saveUser(User user);

    @Select("select * from user where name = #{name} and password = #{password}")
    User findByUsernameAndPassword(User user);

    @Select("select * from user where name = #{name}")
    User findByName(String user);

}
