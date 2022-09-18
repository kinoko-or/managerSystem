package com.kamiii.managersystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kamiii.managersystem.entity.User;
import com.kamiii.managersystem.mapper.UserMapper;
import com.kamiii.managersystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ManagerSystemApplicationTests{

    @Resource
    UserService userService;

    @Test
    void contextLoads(){
    }

    @Test
    public void testPage(){
    }

}
