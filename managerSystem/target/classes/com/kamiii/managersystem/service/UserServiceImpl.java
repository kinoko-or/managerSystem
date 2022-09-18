package com.kamiii.managersystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kamiii.managersystem.entity.User;
import com.kamiii.managersystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> findAllUser(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectList(null);
    }

    @Override
    public IPage<User> selectByCondition(QueryWrapper<User> queryWrapper, Integer pageNum, Integer pageSize){
        Page<User> page;
        if(pageNum != null && pageSize != null){
            page = new Page<>(pageNum, pageSize);
        }else{
            page = new Page<>(1, 5);
        }
        return userMapper.selectPage(page, queryWrapper);
    }

    // @Override
    // public int insertUser(User user){
    //     return userMapper.insert(user);
    // }

    @Override
    public int updateUser(UpdateWrapper<User> uw, User user){
        return userMapper.update(user, uw);
    }

    // @Override
    // public int deleteUser(Long id){
    //     return userMapper.deleteUser(id);
    // }

    @Override
    public int getCount(){
        return userMapper.getCount();
    }
}
