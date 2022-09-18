package com.kamiii.managersystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kamiii.managersystem.entity.User;
import com.kamiii.managersystem.mapper.UserMapper;
import com.kamiii.managersystem.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController{

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @PostMapping("/testO")
    public Boolean testO(){
        return true;
    }

    @PostMapping("/insertUser")
    public boolean insertUser(
            @RequestBody
                    User user){
        return userService.save(user);
    }

    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @GetMapping("/selectByCondition/")
    public Map<String, Object> selectByCondition(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String address,
            Integer pageNum,
            Integer pageSize){
        Map<String, Object> res = new HashMap<>();
        Integer total = userService.getCount();
        QueryWrapper<User> qw = new QueryWrapper<>();
        if(Strings.isNotEmpty(username)){
            qw.like("username", username);
        }
        if(Strings.isNotEmpty(email)){
            qw.like("email", email);
        }
        if(Strings.isNotEmpty(address)){
            qw.like("address", address);
        }
        qw.orderByDesc("id");
        List<User> data = userService.selectByCondition(qw, pageNum, pageSize).getRecords();
        res.put("total", total);
        res.put("data", data);
        return res;
    }

    @PutMapping("/updateUser")
    public Boolean updateUser(
            @RequestBody
                    User user){
        //判断id是否为空，如果为null，则不允许进行修改操作
        if(user.getId() == null || user.getId() < 0){
            return false;
        }
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("id", user.getId());
        return userService.updateUser(uw, user) != 0;
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(
            @PathVariable("id")
                    Long id){
        System.out.println(id);
        if(id == null){
            return false;
        }
        return userService.removeById(id);
    }

    @DeleteMapping("/deleteUser/batch/")
    public boolean deleteUserByBatch(
            @RequestBody
                    List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }

    @GetMapping("/getCount")
    public int getCount(){
        return userService.getCount();
    }

}
