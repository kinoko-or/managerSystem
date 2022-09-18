package com.kamiii.managersystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kamiii.managersystem.entity.User;

import java.util.List;

public interface UserService extends IService<User>{

    List<User> findAllUser();

    IPage<User> selectByCondition(QueryWrapper<User> queryWrapper, Integer pageNum, Integer pageSize);

    // int insertUser(User user);

    int updateUser(UpdateWrapper<User> updateWrapper,User user);

    // int deleteUser(Long id);

    int getCount();
}
