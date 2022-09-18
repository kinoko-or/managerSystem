package com.kamiii.managersystem.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator{

    public static void main(String[] args){
        generator();
    }

    private static void generator(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/kamiii?serverTimezone=GMT%2b8&characterEncoding=utf-8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("kamiii") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            // .fileOverride() // 覆盖已生成文件
                            .outputDir("F:\\Study\\springboot+vue\\managerSystem\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.kamiii.managersystem") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "F:\\Study\\springboot+vue\\managerSystem\\src\\main\\resources\\mapping\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("sys_"); // 设置过滤表前缀
                })
                .execute();
    }
}
