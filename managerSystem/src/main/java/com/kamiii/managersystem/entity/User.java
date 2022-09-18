package com.kamiii.managersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

  @TableId(type = IdType.AUTO)
  private Long id;
  private String username;
  //在前端json中忽略password
  @JsonIgnore
  private String password;
  private String nickname;
  private String email;
  private String phone;
  private String address;
  private Timestamp createTime;
  // @TableLogic
  // private Integer isDelete;

  @Override
  public String toString(){
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", nickname='" + nickname + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", createTime=" + createTime ;
            // + ", isDelete=" + isDelete +
            // '}';
  }
}
