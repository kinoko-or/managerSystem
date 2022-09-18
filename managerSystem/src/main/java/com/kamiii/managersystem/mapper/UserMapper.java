package com.kamiii.managersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kamiii.managersystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{

    // @Select("select * from sys_user")
    List<User> findAllUser();

    // List<User> selectByCondition(User user, Integer pageNum, Integer pageSize);

    // int insertUser(User user);

    // int updateUser(User user);

    // int deleteUser(Long id);

    int getCount();
}
